package com.oetsky.project.serialsetting.serial.utils;

/**
 * @Author: zhangw
 * @Date: 2023/6/15
 */
public enum AddressTransformEnum {

    ADDRESS_1("01", "A", 1),
    ADDRESS_2("02", "B", 1),
    ADDRESS_3("03", "C", 1),

    ADDRESS_4("04", "A", 2),
    ADDRESS_5("05", "B", 2),
    ADDRESS_6("06", "C", 2),

    ADDRESS_7("07", "A", 3),
    ADDRESS_8("08", "B", 3),
    ADDRESS_9("09", "C", 3),

    ADDRESS_10("10", "A", 4),
    ADDRESS_11("11", "B", 4),
    ADDRESS_12("12", "C", 4),

    ADDRESS_13("13", "A", 5),
    ADDRESS_14("14", "B", 5),
    ADDRESS_15("15", "C", 5),

    ADDRESS_16("16", "A", 6),
    ADDRESS_17("17", "B", 6),
    ADDRESS_18("18", "C", 6);

    AddressTransformEnum(String code, String p, int c) {
        this.code = code;
        this.p = p;
        this.c = c;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getP() {
        return p;
    }

    public void setP(String p) {
        this.p = p;
    }

    public Integer getC() {
        return c;
    }

    public void setC(Integer c) {
        this.c = c;
    }

    private String code;
    private String p;
    private Integer c;

    public static AddressTransformEnum getByCode(String code) {
        AddressTransformEnum uploadFileSizeCheck = null;
        for (int i = 0; i < AddressTransformEnum.values().length; i++) {
            uploadFileSizeCheck = AddressTransformEnum.values()[i];
            if (uploadFileSizeCheck.getCode().equals(code)) {
                return uploadFileSizeCheck;
            }
        }
        return null;
    }
}
