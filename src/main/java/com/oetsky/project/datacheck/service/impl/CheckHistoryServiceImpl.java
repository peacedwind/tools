package com.oetsky.project.datacheck.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import com.oetsky.common.utils.StringUtils;
import com.oetsky.common.utils.poi.ExcelUtil;
import com.oetsky.framework.config.OetskyConfig;
import com.oetsky.framework.web.domain.AjaxResult;
import com.oetsky.project.constants.DIffConstants;
import com.oetsky.project.datacheck.domain.CheckHistory;
import com.oetsky.project.datacheck.domain.ErrErrorInfo;
import com.oetsky.project.datacheck.domain.EventInfo;
import com.oetsky.project.datacheck.domain.VoltageErrorInfo;
import com.oetsky.project.datacheck.mapper.CheckHistoryMapper;
import com.oetsky.project.datacheck.service.ICheckHistoryService;
import com.oetsky.project.dataselect.domain.DaVoltageData;
import com.oetsky.project.dataselect.domain.ErrVoltageError;
import com.oetsky.project.dataselect.domain.InspectionEventInfo;
import com.oetsky.project.dataselect.service.IDaVoltageDataService;
import com.oetsky.project.dataselect.service.IErrVoltageErrorService;
import com.oetsky.project.dataselect.service.InspectionEventInfoService;
import com.oetsky.project.enums.CheckResultEnum;
import com.oetsky.project.enums.CheckStatusEnum;
import com.oetsky.project.enums.CheckTypeEnum;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * @description:
 * @author: cyx
 * @date: 2023-06-14
 **/
@Component
public class CheckHistoryServiceImpl implements ICheckHistoryService {

    @Resource
    private CheckHistoryMapper checkHistoryMapper;

    @Resource
    private IDaVoltageDataService daVoltageDataService;

    @Resource
    private IErrVoltageErrorService errVoltageErrorService;

    @Resource
    private InspectionEventInfoService inspectionEventInfoService;

    @Override
    public List<CheckHistory> list(CheckHistory checkHistory) {
        return checkHistoryMapper.list(checkHistory);
    }

    @Override
    public void importCheck(Integer checkType, MultipartFile file) throws Exception{
        Date start = DateUtil.offsetDay(DateUtil.beginOfDay(new Date()),-1);
        Date end = DateUtil.offsetHour(start,2);
        CheckHistory checkHistory = new CheckHistory();
        checkHistory.defaultValue();
        checkHistory.setImportFileName(file.getOriginalFilename());
        boolean flag = true;
        if (checkType == null){
            throw new RuntimeException("校验类型不正确");
        }
        checkHistoryMapper.save(checkHistory);
        if (checkType.equals(CheckTypeEnum.VOLTAGE_DATA_CHECK.getCheckType())){
            checkVoltageData(start,end,checkHistory,file);
        } else if (checkType.equals(CheckTypeEnum.VOLTAGE_ERROR.getCheckType())) {
            checkVoltageError(start,end,checkHistory,file);
        } else if (checkType.equals(CheckTypeEnum.INSPECTION_EVENT.getCheckType())) {
            checkInspectEvent(start,end,checkHistory,file);
        }

    }

    /**
     * 事件校验
     * @param start
     * @param end
     * @param checkHistory
     * @param file
     */
    private void checkInspectEvent(Date start, Date end, CheckHistory checkHistory,
        MultipartFile file) {
        List<EventInfo> res = new ArrayList<>();
        ExcelUtil<EventInfo> exportUtil = null;
        AjaxResult ajaxResult = null;
        boolean flag = true;
        try {
            //解析数据
            ExcelUtil<InspectionEventInfo> inspectionEventInfoExcelUtil = new ExcelUtil<>(InspectionEventInfo.class);
            List<InspectionEventInfo> importList = inspectionEventInfoExcelUtil.importExcel(
                file.getInputStream());
            Map<String, List<InspectionEventInfo>> importMap;
            if (CollectionUtil.isNotEmpty(importList)){
                importMap = importList.stream()
                    .collect(Collectors.groupingBy(InspectionEventInfo::getKey));
            }else {
                importMap = new HashMap<>();
            }
            //从数据库查询数据
            List<InspectionEventInfo> dataBaseList = inspectionEventInfoService.listByCreateTime(
                start,end);
            Map<String, InspectionEventInfo> dataBaseMap;
            if (CollectionUtil.isNotEmpty(dataBaseList)){
                dataBaseMap = dataBaseList.stream()
                    .collect(Collectors.toMap(InspectionEventInfo::getKey,e->e,(a,b)->a));
            }else {
                dataBaseMap = new HashMap<>();
            }
            //已数据的数据为标准 判断数据是否存在缺失 重复 数据是否正确
            for (Entry<String, InspectionEventInfo> entry : dataBaseMap.entrySet()) {
                String key = entry.getKey();
                InspectionEventInfo value = entry.getValue();
                //缺失
                List<InspectionEventInfo> inspectionEventInfos = importMap.get(key);
                if (CollectionUtil.isEmpty(inspectionEventInfos)){
                    res.add(createInfo(value,DIffConstants.LOST));
                    flag = false;
                    continue;
                }
                //重复
                if (inspectionEventInfos.size() > 1){
                    res.add(createInfo(value,DIffConstants.REPEAT));
                    flag = false;
                    continue;
                }
                //数据相对比
                InspectionEventInfo first = CollectionUtil.getFirst(inspectionEventInfos);
                String diffInfo = value.getDiffInfo(first);
                if (StringUtils.isNotBlank(diffInfo)){
                    flag = false;
                    res.add(createInfo(value,diffInfo));
                }
            }
            //导入到数据是否有多余
            Set<String> importSet = importMap.keySet();
            Set<String> dataBaseSet = dataBaseMap.keySet();
            //取差集
            Collection<String> subSet = CollectionUtils.subtract(importSet, dataBaseSet);
            if (CollectionUtil.isNotEmpty(subSet)){
                //有多余的数据
                for (String key : subSet) {
                    List<InspectionEventInfo> inspectionEventInfos  = importMap.get(key);
                    InspectionEventInfo first = CollectionUtil.getFirst(inspectionEventInfos);
                    if (first != null){
                        flag = false;
                        res.add(createInfo(first,DIffConstants.SUPERFLUOUS));
                    }
                }

            }
            if (!flag){
                //排序
                Collections.sort(res);
                //生成excel 放在项目下面
                exportUtil = new ExcelUtil<>(EventInfo.class);
                ajaxResult = exportUtil.exportExcel(res, "异常信息");
                Object msg = ajaxResult.get("msg");
                checkHistory.setCheckStatus(CheckStatusEnum.OVER.getCode());
                checkHistory.setCheckResult(CheckResultEnum.ERROR.getCode());
                checkHistory.setResultFileName(String.valueOf(msg));
                checkHistory.setResultFileUrl(exportUtil.getAbsoluteFile(String.valueOf(msg)));
            }
        } catch (Exception e) {
            //写txt
            checkHistory.setCheckStatus(CheckStatusEnum.OVER.getCode());
            checkHistory.setCheckResult(CheckResultEnum.ERROR.getCode());
            String resultFile = UUID.randomUUID() + ".txt";
            String absoluteFile = getAbsoluteFile(resultFile);
            checkHistory.setResultFileName(resultFile);
            checkHistory.setResultFileUrl(absoluteFile);
            FileUtil.writeBytes(e.getMessage().getBytes(StandardCharsets.UTF_8),absoluteFile);
        } finally {
            //save
            checkHistoryMapper.updateById(checkHistory);
        }

    }

    private void checkVoltageError(Date start, Date end, CheckHistory checkHistory,
        MultipartFile file) {
        List<ErrErrorInfo> res = new ArrayList<>();
        ExcelUtil<ErrErrorInfo> exportUtil = null;
        AjaxResult ajaxResult = null;
        boolean flag = true;
        try {
            //解析数据
            ExcelUtil<ErrVoltageError> errVoltageErrorExcelUtil = new ExcelUtil<>(ErrVoltageError.class);
            List<ErrVoltageError> importList = errVoltageErrorExcelUtil.importExcel(
                file.getInputStream());
            Map<String, List<ErrVoltageError>> importMap;
            if (CollectionUtil.isNotEmpty(importList)){
                importMap = importList.stream()
                    .collect(Collectors.groupingBy(ErrVoltageError::getKey));
            }else {
                importMap = new HashMap<>();
            }
            //从数据库查询数据
            ErrVoltageError errVoltageError = new ErrVoltageError();
            errVoltageError.setCalculateTimeStart(start);
            errVoltageError.setCalculateTimeEnd(end);
            List<ErrVoltageError> dataBaseList = errVoltageErrorService.selectErrVoltageErrorList(
                errVoltageError);
            Map<String, ErrVoltageError> dataBaseMap;
            if (CollectionUtil.isNotEmpty(dataBaseList)){
                dataBaseMap = dataBaseList.stream()
                    .collect(Collectors.toMap(ErrVoltageError::getKey,e->e,(a,b)->a));
            }else {
                dataBaseMap = new HashMap<>();
            }
            //已数据的数据为标准 判断数据是否存在缺失 重复 数据是否正确
            for (Entry<String, ErrVoltageError> entry : dataBaseMap.entrySet()) {
                String key = entry.getKey();
                ErrVoltageError value = entry.getValue();
                //缺失
                List<ErrVoltageError> errVoltageErrors = importMap.get(key);
                if (CollectionUtil.isEmpty(errVoltageErrors)){
                    res.add(createInfo(value,DIffConstants.LOST));
                    flag = false;
                    continue;
                }
                //重复
                if (errVoltageErrors.size() > 1){
                    res.add(createInfo(value,DIffConstants.REPEAT));
                    flag = false;
                    continue;
                }
                //数据相对比
                ErrVoltageError first = CollectionUtil.getFirst(errVoltageErrors);
                String diffInfo = value.getDiffInfo(first);
                if (StringUtils.isNotBlank(diffInfo)){
                    flag = false;
                    res.add(createInfo(value,diffInfo));
                }
            }
            //导入到数据是否有多余
            Set<String> importSet = importMap.keySet();
            Set<String> dataBaseSet = dataBaseMap.keySet();
            //取差集
            Collection<String> subSet = CollectionUtils.subtract(importSet, dataBaseSet);
            if (CollectionUtil.isNotEmpty(subSet)){
                //有多余的数据
                for (String key : subSet) {
                    List<ErrVoltageError> errVoltageErrors = importMap.get(key);
                    ErrVoltageError first = CollectionUtil.getFirst(errVoltageErrors);
                    if (first != null){
                        flag = false;
                        res.add(createInfo(first,DIffConstants.SUPERFLUOUS));
                    }
                }

            }
            if (!flag){
                //排序
                Collections.sort(res);
                //生成excel 放在项目下面
                exportUtil = new ExcelUtil<>(ErrErrorInfo.class);
                ajaxResult = exportUtil.exportExcel(res, "异常信息");
                Object msg = ajaxResult.get("msg");
                checkHistory.setCheckStatus(CheckStatusEnum.OVER.getCode());
                checkHistory.setCheckResult(CheckResultEnum.ERROR.getCode());
                checkHistory.setResultFileName(String.valueOf(msg));
                checkHistory.setResultFileUrl(exportUtil.getAbsoluteFile(String.valueOf(msg)));
            }
        } catch (Exception e) {
            //写txt
            checkHistory.setCheckStatus(CheckStatusEnum.OVER.getCode());
            checkHistory.setCheckResult(CheckResultEnum.ERROR.getCode());
            String resultFile = UUID.randomUUID() + ".txt";
            String absoluteFile = getAbsoluteFile(resultFile);
            checkHistory.setResultFileName(resultFile);
            checkHistory.setResultFileUrl(absoluteFile);
            FileUtil.writeBytes(e.getMessage().getBytes(StandardCharsets.UTF_8),absoluteFile);
        } finally {
            //save
            checkHistoryMapper.updateById(checkHistory);
        }
    }

    private ErrErrorInfo createInfo(ErrVoltageError errVoltageError,String errorInfo){
        ErrErrorInfo errErrorInfo = new ErrErrorInfo();
        if (errVoltageError != null){
            errErrorInfo.setChannelNum(errVoltageError.getChannelNum());
            errErrorInfo.setCollectionTime(errVoltageError.getCalculateTime());
        }
        errErrorInfo.setErrInfo(errorInfo);
        return errErrorInfo;

    }


    private EventInfo createInfo(InspectionEventInfo inspectionEventInfo,String errorInfo){
        EventInfo eventInfo = new EventInfo();
        if (errorInfo != null){
            eventInfo.setId(inspectionEventInfo.getId());
        }
        eventInfo.setErrInfo(errorInfo);
        return eventInfo;

    }

    private void checkVoltageData(Date start,Date end,CheckHistory checkHistory, MultipartFile file) {
        List<VoltageErrorInfo> res = new ArrayList<>();
        ExcelUtil<VoltageErrorInfo> exportUtil = null;
        AjaxResult ajaxResult = null;
        boolean flag = true;
        try {
            //解析数据
            ExcelUtil<DaVoltageData> daVoltageDataExcelUtil = new ExcelUtil<>(DaVoltageData.class);
            List<DaVoltageData> importList = daVoltageDataExcelUtil.importExcel(
                file.getInputStream());
            Map<String, List<DaVoltageData>> importMap;
            if (CollectionUtil.isNotEmpty(importList)){
                importMap = importList.stream()
                    .collect(Collectors.groupingBy(DaVoltageData::getKey));
            }else {
                importMap = new HashMap<>();
            }
            //从数据库查询数据
            DaVoltageData selectDaVoltageData = new DaVoltageData();
            selectDaVoltageData.setCollectTimeStart(start);
            selectDaVoltageData.setCollectTimeEnd(end);
            List<DaVoltageData> dataBaseList = daVoltageDataService.selectDaVoltageDataList(
                selectDaVoltageData);
            Map<String, DaVoltageData> dataBaseMap;
            if (CollectionUtil.isNotEmpty(dataBaseList)){
                dataBaseMap = dataBaseList.stream()
                    .collect(Collectors.toMap(DaVoltageData::getKey,e->e,(a,b)->a));
            }else {
                dataBaseMap = new HashMap<>();
            }
            //已数据的数据为标准 判断数据是否存在缺失 重复 数据是否正确
            for (Entry<String, DaVoltageData> entry : dataBaseMap.entrySet()) {
                String key = entry.getKey();
                DaVoltageData value = entry.getValue();
                //缺失
                List<DaVoltageData> daVoltageData = importMap.get(key);
                if (CollectionUtil.isEmpty(daVoltageData)){
                    res.add(createInfo(value,DIffConstants.LOST));
                    flag = false;
                    continue;
                }
                //重复
                if (daVoltageData.size() > 1){
                    res.add(createInfo(value,DIffConstants.REPEAT));
                    flag = false;
                    continue;
                }
                //数据相对比
                DaVoltageData first = CollectionUtil.getFirst(daVoltageData);
                String diffInfo = value.getDiffInfo(first);
                if (StringUtils.isNotBlank(diffInfo)){
                    flag = false;
                    res.add(createInfo(value,diffInfo));
                }
            }
            //导入到数据是否有多余
            Set<String> importSet = importMap.keySet();
            Set<String> dataBaseSet = dataBaseMap.keySet();
            //取差集
            Collection<String> subSet = CollectionUtils.subtract(importSet, dataBaseSet);
            if (CollectionUtil.isNotEmpty(subSet)){
                //有多余的数据
                for (String key : subSet) {
                    List<DaVoltageData> daVoltageData = importMap.get(key);
                    DaVoltageData first = CollectionUtil.getFirst(daVoltageData);
                    if (first != null){
                        flag = false;
                        res.add(createInfo(first,DIffConstants.SUPERFLUOUS));
                    }
                }

            }
            if (!flag){
                //排序
                Collections.sort(res);
                //生成excel 放在项目下面
                exportUtil = new ExcelUtil<>(VoltageErrorInfo.class);
                ajaxResult = exportUtil.exportExcel(res, "异常信息");
                Object msg = ajaxResult.get("msg");
                checkHistory.setCheckStatus(CheckStatusEnum.OVER.getCode());
                checkHistory.setCheckResult(CheckResultEnum.ERROR.getCode());
                checkHistory.setResultFileName(String.valueOf(msg));
                checkHistory.setResultFileUrl(exportUtil.getAbsoluteFile(String.valueOf(msg)));
            }
        } catch (Exception e) {
            //写txt
            checkHistory.setCheckStatus(CheckStatusEnum.OVER.getCode());
            checkHistory.setCheckResult(CheckResultEnum.ERROR.getCode());
            String resultFile = UUID.randomUUID() + ".txt";
            String absoluteFile = getAbsoluteFile(resultFile);
            checkHistory.setResultFileName(resultFile);
            checkHistory.setResultFileUrl(absoluteFile);
            FileUtil.writeBytes(e.getMessage().getBytes(StandardCharsets.UTF_8),absoluteFile);
        } finally {
            //save
            checkHistoryMapper.updateById(checkHistory);
        }
    }

    private VoltageErrorInfo createInfo(DaVoltageData daVoltageData,String errorInfo){
        VoltageErrorInfo voltageErrorInfo = new VoltageErrorInfo();
        if (daVoltageData != null){
            voltageErrorInfo.setChannelNum(daVoltageData.getChannelNum());
            voltageErrorInfo.setCollectionTime(daVoltageData.getCollectTime());
        }
        voltageErrorInfo.setErrInfo(errorInfo);
        return voltageErrorInfo;
    }

    public String getAbsoluteFile(String filename) {
        String downloadPath = OetskyConfig.getDownloadPath() + filename;
        File desc = new File(downloadPath);
        if (!desc.getParentFile().exists()) {
            desc.getParentFile().mkdirs();
        }
        return downloadPath;
    }

    @Override
    public CheckHistory selectById(Integer id) {
        return checkHistoryMapper.selectById(id);
    }
}
