package com.oetsky.project.serialsetting.serial.domain;


/**
 * 698通用常量
 */
public class DLT698Constants {
    public static final String OCTET_STR = "55";
    public static final String OAD_STR = "51";
    public static final String ENUM_STR = "16";
    public static final String LONG64_UNSIGEND = "15";
    public static final String LONG64 = "14";
    public static final String LONG_UNSIGNED = "12";
    public static final String UNSIGNED = "11";
    public static final String LONG_STR = "10";
    public static final String OCTET_STRING = "09";
    public static final String OCTET_LEN = "07";
    public static final String DOUBLE_LONG_UNSIGEND = "06";
    public static final String DOUBLE_LONG = "05";
    public static final String BIT_STRING = "04";
    public static final String STRUCT = "02";
    public static final String ARRAY_LEN = "01";
    /**
     * 采集状态字段 位数长度
     */
    public static final String DATA_TYPE_LENG = "01";
    public static final String DATE_TIME_S = "1c";
    public static final String VISIBLE_STRING = "0A";
    public static final String NULL_STR = "00";
    /**
     * 事件数据 or  冻结数据
     */
    public static final String CSD_TYPE = "00";

    /**
     * 数据标识
     */
    public enum DataType {
        LIGHTPORT("01", "LIGHTPORT", "光口"),
        NETPORT("02", "NETPORT", "网口"),
        SERIAL("03", "SERIAL", "串口"),
        OTHER("04", "OTHER", "其他");

        private String terminalCode;

        private String code;

        private String desc;

        DataType(String terminalCode, String code, String desc) {
            this.terminalCode = terminalCode;
            this.code = code;
            this.desc = desc;
        }

        /**
         * 获取
         *
         * @return terminalCode
         */
        public String getTerminalCode() {
            return this.terminalCode;
        }

        /**
         * 设置
         *
         * @param terminalCode
         */
        public void setTerminalCode(String terminalCode) {
            this.terminalCode = terminalCode;
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
     * 异常类型
     */
    public enum DateError {

        EMPTY("01", "EMPTY", "采集设备无数据"),
        ERROR("02", "ERROR", "采集设备数据异常"),
        NOTOPEN("03", "NOTOPEN", "接口未打开"),
        U_ERROR_24("04", "04", "误差警告"),
        HL_ERROR_24("05", "05", "误差异常"),
        UB_ERROR_24("06", "06", "频率超限"),
        ;

        private String terminalCode;

        private String code;

        private String desc;

        DateError(String terminalCode, String code, String desc) {
            this.terminalCode = terminalCode;
            this.code = code;
            this.desc = desc;
        }

        /**
         * 获取
         *
         * @return terminalCode
         */
        public String getTerminalCode() {
            return this.terminalCode;
        }

        /**
         * 设置
         *
         * @param terminalCode
         */
        public void setTerminalCode(String terminalCode) {
            this.terminalCode = terminalCode;
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
     * 冻结数据
     */
    public enum FrozenDataFlag {
        CVT_MONITORING_DATA("20680200", "monitoringData", "互感器监测数据"),
        CVT_ERROR_DATA("20690200", "errorData", "互感器误差数据"),
        CVT_FROZEN_TIME_DATA("20210200", "frozenTime", "冻结时间"),
        CVT_DETECTS_HARDWARE_INFORMATION("40420200", "detectHarawareInformation", "互感器检测硬件状态信息"),
        CVT_ACQUISITION_OF_DEVICE_TYPE("40360200", "cvtAcquisitionOfDeviceType", "互感器采集设备类型"),
        CVT_ACQUISITION_COMPARISON_DATA("20700200", "cvtAcquisitionOfComparisonType", "互感器误差评估数据(基于参考标准器)"),
        ;
        private String code;

        private String fileName;

        private String desc;

        FrozenDataFlag(String code, String fileName, String desc) {
            this.code = code;
            this.fileName = fileName;
            this.desc = desc;
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
         * 获取
         *
         * @return fileName
         */
        public String getFileName() {
            return this.fileName;
        }

        /**
         * 设置
         *
         * @param fileName
         */
        public void setFileName(String fileName) {
            this.fileName = fileName;
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
     * 事件数据
     */
    public enum EventDataFlag {

        EVENT_ID("20220200", "eventId", "事件记录序号"),
        START_TIME("201E0200", "startTime", "事件发生时间"),
        END_TIME("20200200", "endTime", "事件结束时间"),

        EVENT_SOURCE("20240200", "eventSource", "事件源"),
        EVENT_REPORT_TYPE("33000200", "eventReportType", "异常事件上报状态"),
        EVENT_CONTROL_TYPE("33430206", "eventControlType", "事件接口类型"),
        EVENT_ERROR_TYPE("33430207", "eventErrorType", "事件异常类型"),
        EVENT_MONITORING_DATA("20680200", "eventMonitoringData", "事件抽样异常数据"),
        EVENT_MONITORING_DATA_ERR08("33430208", "eventMonitoringData", "事件抽样异常数据"),
        EVENT_ERROR_DATA("20690200", "eventErrorData", "事件误差异常数据"),
        EVENT_ERROR_DATA_ERR09("33430209", "eventErrorData", "事件误差异常数据"),

        ;

        private String code;

        private String fileName;

        private String desc;

        EventDataFlag(String code, String fileName, String desc) {
            this.code = code;
            this.fileName = fileName;
            this.desc = desc;
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
         * 获取
         *
         * @return fileName
         */
        public String getFileName() {
            return this.fileName;
        }

        /**
         * 设置
         *
         * @param fileName
         */
        public void setFileName(String fileName) {
            this.fileName = fileName;
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
