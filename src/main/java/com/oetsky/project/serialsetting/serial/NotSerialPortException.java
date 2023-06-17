package com.oetsky.project.serialsetting.serial;

public class NotSerialPortException extends Exception {

    private String appName;

    public NotSerialPortException(String appName) {
        super(appName + "不是串口类型");
        this.appName = appName;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }
}
