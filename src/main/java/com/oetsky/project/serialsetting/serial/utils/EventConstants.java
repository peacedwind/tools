package com.oetsky.project.serialsetting.serial.utils;

/**
 * @Author: zhangw
 * @Date: 2023/6/15
 */
public class EventConstants {
    /**
     * CVT通讯设备类型
     */
    public enum SysCommunicationType {

        SERIAL_ALL("0", "全部"),
        SERIAL_RS_645("1", "威胜电能表"),
        SERIAL_DLT_698("2", "能源控制器");

        private String code;

        private String desc;

        SysCommunicationType(String code, String desc) {
            this.code = code;
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
    }
}

