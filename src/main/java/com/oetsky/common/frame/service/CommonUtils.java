package com.oetsky.common.frame.service;

import com.oetsky.common.frame.frameBean.MergingUnitClasses;
import java.util.HashMap;
import java.util.Map;

public class CommonUtils {

    // TODO xzc 多采集单元完成后，检查此处是否还有引用，如有则修改
    public static Long UNIT_ID_DEFUALT = 526L;

    public static Map<String, MergingUnitClasses> mapClass = new HashMap<>();

    public static MergingUnitClasses getMergingUnitClasses(String logicAddress) {
        return mapClass.get(logicAddress);
    }

}
