package com.oetsky.project.serialsetting.serial.utils;

/**
 * @author: xiangzc
 * @date: 2021-3-12
 */
public class ControlConstants {

    /**
     * 登录
     */
    public static final int LOGIN_TYPE = 0;
    public static Boolean LOGIN_STATUS= Boolean.FALSE;


    /**
     * 心跳
     */
    public static final int HEARTBEAT_TYPE = 1;
    public static Boolean HEARTBEAT_STATUS= Boolean.FALSE;

    /**
     * 公共硬件信息
     */
//    public static Server PUBLIC_SERVICE_INFO = null;

    /**
     * 唤醒
     */
    public static final String AWAKEN_COMMAND_FE = "FEFEFEFE";

    /**
     * 帧头
     */
    public static final String FRAME_HEADER_68 = "68";

    /**
     * 帧尾
     */
    public static final String FRAME_FOOTER_16 = "16";


    /**
     * 帧头长度占位
     */
    public static final int HEADER_LENGTH = 1 * 2;
    /**
     * 数据长度占位
     */
    public static final int CONTROL_LENGTH = 3 * 2;
    /**
     * 控制域长度
     */
    public static final int REGION_LENGTH = 4 * 2;
    /**
     * 地址类型
     */
    public static final int REGION_TYPE_LENGTH = 5 * 2;
    /**
     * 服务器地址SA
     */
    public static final int SERVICE_ADDRESS_LENGTH = 11 * 2;
    /**
     * 客户机地址CA
     */
    public static final int CUSTOMER_ADDRESS_LENGTH = 12 * 2;
    /**
     * 帧头校验HCS
     */
    public static final int HEADER_CHECKS_LENGTH = 14 * 2;

    /**
     * 帧校验FCS
     */
    public static final int FINISH_CHECKS_LENGTH = -3 * 2;
    /**
     * 帧校验FCS
     */
    public static final int FOOTER_LENGTH = -1 * 2;

    /**
     * 终端命令帧控制码
     * 积成电子自定义控制码 写死
     */
    public static final String CONTROL_CODE_1D = "1D";

    /**
     * 数据域长度标识
     */
    public static final String DATA_LENGTH_FLAG = "#CD$";
//    public static final String DATA_LENGTH_FLAG = "#DATA_LENGTH#";

    /**
     * 帧头长度标识
     */
    public static final String FRAME_HEADER_FLAG = "#ZT$";
    /**
     * 互感器监测数据 请求响应（单个）
     */
    public static final String FRAME_RESPONSE_NORMAL = "8501";
    /**
     * 主动上报通知（单个）发送
     */
    public static final String FRAME_REPORT_NORMAL = "8801";
    /**
     * 补召分帧请求
     */
    public static final String FRAME_REPORT_NEXT_TYPE = "0505";
    /**
     * 补召分帧响应
     */
    public static final String FRAME_RESPONSE_NEXT_TYPE = "8505";
    /**
     * 补召响应（冻结数据）
     */
    public static final String FRAME_RESPONSE_FREZZE_TYPE = "8503";
    /**
     * 请求类型：登录
     */
    public static final String FRAME_POST_LOGIN = "010000";
    /**
     * 请求类型：登录
     */
    public static final String FRAME_POST_HEARTBEAT = "010101";
    /**
     * 互感器监测数据 请求响应（集合）
     */
    public static final String FRAME_RESPONSE_NORMAL_LIST = "8502";
    /**
     * 互感器监测数据 请求响应
     */
    public static final String FRAME_RESPONSE_NORMAL_PEERTTIME = "8701";
    /**
     * 互感器监测数据 PIIDACD
     */
    public static final String FRAME_REQUEST_PIIDACD = "0d";
    /**
     * 互感器监测数据 请求响应数据类型及长度
     */
    public static final String FRAME_REQUEST_NORMAL_OAD = "0200";
    /**
     * 互感器采集异常事件记录单元 请求响应数据类型及长度
     */
    public static final String FRAME_INCIDENT_RECORD_OAD = "0600";
    /**
     * 发送 GetResultType
     */
    public static final String FRAME_RESULT_TYPE = "01";
    /**
     * 互感器监测数据 发送数据长度
     */
    public static final String FRAME_MONITORING_DATA_LEN = "020a";
    /**
     * 互感器监测数据 发送数据长度
     */
    public static final String FRAME_ERROR_DATA_LEN = "0204";
    /**
     * 互感器检测硬件状态信息 发送数据长度
     */
    public static final String FRAME_HARDWARE_STATUS_LEN = "0201";
    /**
     * 互感器采集异常事件记录单元 发送数据长度
     */
    public static final String FRAME_INCIDENT_RECORD_LEN = "02";
    /**
     * 互感器采集异常事件   数据类型
     */
    public static final String FRAME_FRAME_INCIDENT_RECORD_LEN = "01";
    /**
     * 跟随上报信息域
     */
    public static final String FRAME_FOLLOW_REPORT = "00";
    /**
     * 上报等待时长
     */
    public static final String FRAME_AWAIT_TIME = "000000";

    /**
     * 帧尾校验长度标识
     */
    public static final String FRAME_FOOTER_FLAG = "#ZW$";

    /**
     * 数据内容
     */
    public static final String FRAME_CONTENT = "#CONTENTS$";
    /**
     * 抄读表地址 PIIDACD  calculationaddress
     */
    public static final String FRAME_CALCULATION_ADDERSS_PIIDACD = "00";

    /**
     * 事件数据 请求响应
     */
    public static final String FRAME_EVENT_DATA_RESPONSE_NORMAL = "8503";
    /**
     * 事件数据 PIIDACD  eventData
     */
    public static final String FRAME_EVENT_DATA_PIIDACD = "01";
    /**
     * 冻结数据 PIIDACD  frozenData
     */
    public static final String FRAME_ROZEN_DATA_PIIDACD = "07";

    /**
     * 现场站应答帧控制码
     * 控制码 写死
     */
    public static final String CONTROL_CODE_C3 = "c3";

    /**
     * 现场站 登录控制域
     * 控制码 写死
     */
    public static final String CONTROL_LOGIN = "81";

    /**
     * 现场站 登录控制域
     * 控制码 写死
     */
    public static final String NOTARIZE_LOGIN = "80";
    /**
     * 现场站应答帧控制码
     * 控制码 写死
     */
    public static final String CONTROL_REGION_TYPE = "05";
    public static final String CONTROL_CUSTOMER_ADDRESS = "10";
    /**
     * 登录地址域
     */
    public static final String CONTROL_LOGIN_ADDRESS = "10";

    /**
     * 异常日志 控制码
     */
    public static final String CONTROL_CODE_91 = "91";

    /**
     * 定死 冻结密度 15分钟
     */
    public static final String FREEZING_DENSITY_15 = "15";

    /**
     * 定死 查询点数 1个点
     */
    public static final String QUERY_POINTS_01 = "01";

    /**
     * 数据域 帧 二进制 最高位 0为正 1为负
     */
    public static final String POSITIVE_0 = "0";

    /**
     * 数据域 帧 二进制 最高位 0为正 1为负
     */
    public static final String NEGATIVE_1 = "1";

    /**
     * 地址域长度 6字节 * 2
     */
    private static final int ADDR_FIELD_LENGTH = 6 * 2;

    /**
     * 测量时间长度 5字节 * 2
     */
    private static final int MEASURE_TIME_LENGTH = 5 * 2;

    /**
     * 地址域开始下标
     */
    public static final int ADDR_FIELD_START_INDEX = 10;

    /**
     * 地址域结束下标
     */
    public static final int ADDR_FIELD_END_INDEX = ADDR_FIELD_START_INDEX + ADDR_FIELD_LENGTH;

    /**
     * 测量时间开始下标
     */
    public static final int MEASURE_TIME_START_INDEX = 40;


    /**
     * 测量时间结束下标
     */
    public static final int MEASURE_TIME_END_INDEX = MEASURE_TIME_START_INDEX + MEASURE_TIME_LENGTH;

    /*****************************************广播校时*************************************************************/
    /**
     * 广播校时 时间长度
     */
    public static final int CORRECTING_TIME_LENGTH = 12;

    /**********************************************************************************************************/

    /**
     * 地址域标识
     */
    public static final String ADDR_FIELD_FLAG = "#ADDR_FIELD#";

    /**
     * 地址域标识
     */
    public static final String CS_FLAG = "#CS#";

    /**
     * 数据为空时 应答
     * 异常应答帧
     */
    public static final String DATA_EMPTY_FRAME = "68" + ADDR_FIELD_FLAG + "68DD0102" + CS_FLAG + "16";

    /**
     * 空时间 用 000000000000 表示
     */
    public static final String EMPTY_DATE = "000000000000";

    /**
     * 采集异常次数最大值
     */
    public static final int MAX_ERROR_COUNT = 999999;


    /**
     * 数据标识
     */
    public enum DataFlag {

        CVT_MONITORING_DATA("2068", 54, "互感器监测数据"),
        CVT_ERROR_DATA("2069", 54, "互感器误差数据"),
        CVT_COMPARISON_DATA("2070", 54, "互感器对比数据"),
        CVT_ACQUISITION_OF_ABNORMAL_EVENTS("304C", 40, "互感器采集异常事件"),

        CVT_ACQUISITION_OF_DEVICE_TYPE("4036", 40, "互感器采集设备类型"),


        CVT_DETECTS_HARDWARE_INFORMATION("4042", 40, "互感器检测硬件状态信息"),
        CVT_ACQUISITION_OF_ABNORMAL_EVENTS_RECORDS("3343", 40, "互感器采集异常事件记录单元"),
        CVT_READ_ADDRESS_TABLE("4002", 40, "抄读表地址"),
        CVT_POSITIVE_ACTIVE_ELECTRIC_ENERGY("0010", 40, "正向有功电能"),
        CVT_FROZEN_DATA("5002", 40, "冻结数据"),
        CVT_EVENT_DATA("301B", 40, "事件数据"),

        CVT_DAYS_FROZEN_DATA("5004", 40, "日冻结数据"),
        CVT_MONTHS_FROZEN_DATA("5006", 40, "月冻结数据"),
        CVT_YEARS_FROZEN_DATA("5007", 40, "年冻结数据"),

        CVT_DETECTS_HARDWARE_ADDRESS("4043", 40, "CVT在线监测硬件资产编号"),

        CVT_REAL_DATA("1234", 40, "实时数据"),

        SAMPLE("20040100", 54, "抽样数据"),
        CALCULATE("20040300", 54, "计算数据"),

        /****** 新增OAD-start ******/
        CVT_COLLECT_DATA("2280", 104, "电压互感器采集数据"),
        CVT_CALCULATE_DATA("2281", 83, "电压互感器计算数据"),
        CT_COLLECT_DATA("2282", 104, "电流互感器采集数据"),
        CT_CALCULATE_DATA("2283", 83, "电流互感器计算数据"),
        METER_COLLECT_DATA("2284", 95, "电能表采集数据"),
        METER_CALCULATE_DATA("2285", 71, "电能表计算数据"),
        EVENT_DATA("2286", 38, "事件数据"),
        EVENT_DATA_NEW("3286", 38, "事件数据"),
        /****** 新增OAD-end ******/

        COLLECT_ERROR_COUNT("20040500", 40, "异常发生次数"),

        COLLECT_ERROR("200404", 40, "上n次异常公共帧"),
        COLLECT_ERROR_1("20040401", 40, "上1次异常"),
        COLLECT_ERROR_2("20040402", 40, "上2次异常"),
        COLLECT_ERROR_3("20040403", 40, "上3次异常"),
        COLLECT_ERROR_4("20040404", 40, "上4次异常"),
        COLLECT_ERROR_5("20040405", 40, "上5次异常"),
        COLLECT_ERROR_6("20040406", 40, "上6次异常"),
        COLLECT_ERROR_7("20040407", 40, "上7次异常"),
        COLLECT_ERROR_8("20040408", 40, "上8次异常"),
        COLLECT_ERROR_9("20040409", 40, "上9次异常"),
        COLLECT_ERROR_10("20040410", 40, "上10次异常"),
        COLLECT_ERROR_0A("2004040A", 40, "上10次异常(0A)"),
        /**
         * 广播校时
         * 包含前面的字符串就是广播校时
         */
//        CORRECTING_TIME("4204",40,"广播校时")
        CORRECTING_TIME("40007F00", 40, "广播校时"),
        CORRECTING_TIME_STR("AA", 40, "点对点校时地址标签"),
        CORRECTING_PEER_TO_PEER_TIME("40000200", 40, "点对点校时"),


        FREEZE_DATA_TASK("60120300",40,"任务配置集合");

        private String code;

        /**
         * 命令帧长度
         */
        private int length;

        private String desc;

        DataFlag(String code, int length, String desc) {
            this.code = code;
            this.length = length;
            this.desc = desc;
        }

        public static String getDescName(String code){
            for (DataFlag dataFlag : DataFlag.values()) {
                if (dataFlag.getCode().equals(code) || code.contains(dataFlag.getCode())) {
                    return dataFlag.getDesc();
                }
            }
            return "";
        }
        /**
         * 获取
         *
         * @return code
         */
        public String getCode() {
            return this.code;
        }

        /**
         * 设置
         *
         * @param code
         */
        public void setCode(String code) {
            this.code = code;
        }

        /**
         * 获取 命令帧长度
         *
         * @return length 命令帧长度
         */
        public int getLength() {
            return this.length;
        }

        /**
         * 设置 命令帧长度
         *
         * @param length 命令帧长度
         */
        public void setLength(int length) {
            this.length = length;
        }

        /**
         * 获取
         *
         * @return desc
         */
        public String getDesc() {
            return this.desc;
        }

        /**
         * 设置
         *
         * @param desc
         */
        public void setDesc(String desc) {
            this.desc = desc;
        }
    }

    /**
     * 抽样数据字段 数据格式
     */
    public enum ColumnFormat {
        /**
         * 互感器监测数据
         */
        FUNDAMENTAL_FREQUENCY("0000.0000", 4 * 2, "基波频率"),
        FUNDAMENTAL_AMPLITUDE("000000000000.0000", 8 * 2, "基波有效值"),
        HARMONIC_AMPLITUDE_3("000000000000.0000", 8 * 2, "3次谐波有效值"),
        HARMONIC_AMPLITUDE_5("000000000000.0000", 8 * 2, "5次谐波有效值"),
        FUNDAMENTAL_PHASE("000000.00", 4 * 2, "基波相位"),
        HARMONIC_PHASE_3("000000.00", 4 * 2, "3次谐波相位"),
        HARMONIC_PHASE_5("000000.00", 4 * 2, "5次谐波相位"),
        ZERO_SEQUENCE_VOLTAGE_IMBALANCE("000000.0000", 5 * 2, "零序电压不平衡度"),
        NEGATIVE_SEQUENCE_VOLTAGE_IMBALANCE("000000.0000", 5 * 2, "负序电压不平衡度"),
        /**
         * 温湿度
         */
        TEMPERATURE("000.0", 2 * 2, "温度"),
        HUMIDITY("000.0", 2 * 2, "湿度"),

        /**
         * 互感器误差数据
         */
        RATIO_AVERAGE("00000.000", 4 * 2, "比差均值"),
        RATIO_VARIANCE("000000000000.0000", 8 * 2, "比差方差"),
        RATIO_AVERAGE_NEW("00000000.00000000",8*2,"比差均值(新)"),
        ANGLE_AVERAGE("00000.000", 4 * 2, "角差均值"),
        ANGLE_AVERAGE_OLD("000000.00", 4 * 2, "角差均值"),
        /**
         * 按标准协议规范 此处是为-2，但在湖北、新疆、福建均为-3转换
         */
        ANGLE_AVERAGE_CRITERION("000000.00", 4 * 2, "角差均值"),
        ANGLE_VARIANCE("000000000000.0000", 8 * 2, "角差方差"),
        ANGLE_AVERAGE_NEW("00000000.00000000",8*2,"角差均值(新)"),

        /**
         * 冻结数据
         */
        FROZEN_DATA_VOLTAGE("0000", 2 * 2, "电压"),
        FROZEN_DATA_ELECTRICITY("00000000", 4 * 2, "电流/有功功率"),

        /**
         * 事件数据
         */
        EVENT_DATA_POSITIVE_ACTIVE("00000000", 4 * 2, "事件记录序号/正向有功电能"),
        EVENT_DATA_VALUE("00.00", 2 * 2, "异常值"),

        /**
         * 正向有功电能
         */
        POSITIVE_ACTIVE("00000000", 4 * 2, "正向有功电能"),

        /**
         * 有功电能
         */
        ACTIVE_ENERGY("00000000.00000", 8 * 2, "有功电能"),

        /**
         * 正向有功电能
         */
        POSITIVE_ACTIVE_ENERGY("00000000.00000", 8 * 2, "正向有功电能"),

        /**
         * 反向有功电能
         */
        REVERSE_ACTIVE_ENERGY("00000000.00000", 8 * 2, "反向有功电能"),

        /**
         * 无功电能
         */
        REACTIVE_ENERGY("00000000.00000", 8 * 2, "无功电能"),

        /**
         * 正向无功电能
         */
        POSITIVE_REACTIVE_ENERGY("00000000.00000", 8 * 2, "正向无功电能"),

        /**
         * 反向有功电能
         */
        REVERSE_REACTIVE_ENERGY("00000000.00000", 8 * 2, "反向无功电能"),

        /**
         * 总电能
         */
        TOTAL_ENERGY("00000000.00000", 8 * 2, "总电能"),

        /**
         * 评估误差
         */
        EVALUATE_ERROR("00000.00000", 4 * 2, "评估误差"),

        /**
         * 置信度
         */
        CONFIDENCE_DEGREE("000.00000", 4 * 2, "置信度"),

        /**
         * 互感器采集异常事件
         */
        ERROR_INCIDENT_RECORD("00000000", 4 * 2, "事件记录序号"),
        ERROR_START_TIME("yyyyMMddHHmmss", 7 * 2, "异常开始时间"),
        ERROR_END_TIME("yyyyMMddHHmmss", 7 * 2, "异常开始时间"),
        INTERFACE_TYPE("00", 1 * 2, "接口类型"),
        ERROR_TYPE("00", 1 * 2, "异常类型");

        /**
         * 数据格式
         */
        private String decimalFormat;

        /**
         * 字段数据的长度
         */
        private int columnLength;

        private String desc;

        ColumnFormat(String decimalFormat, int columnLength, String desc) {
            this.decimalFormat = decimalFormat;
            this.columnLength = columnLength;
            this.desc = desc;
        }

        /**
         * 获取 数据格式
         *
         * @return decimalFormat 数据格式
         */
        public String getDecimalFormat() {
            return this.decimalFormat;
        }

        /**
         * 设置 数据格式
         *
         * @param decimalFormat 数据格式
         */
        public void setDecimalFormat(String decimalFormat) {
            this.decimalFormat = decimalFormat;
        }

        /**
         * 获取 字段数据的长度
         *
         * @return columnLength 字段数据的长度
         */
        public int getColumnLength() {
            return this.columnLength;
        }

        /**
         * 设置 字段数据的长度
         *
         * @param columnLength 字段数据的长度
         */
        public void setColumnLength(int columnLength) {
            this.columnLength = columnLength;
        }

        /**
         * 获取
         *
         * @return desc
         */
        public String getDesc() {
            return this.desc;
        }

        /**
         * 设置
         *
         * @param desc
         */
        public void setDesc(String desc) {
            this.desc = desc;
        }
    }

}
