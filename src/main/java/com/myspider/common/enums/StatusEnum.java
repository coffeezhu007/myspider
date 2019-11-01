package com.myspider.common.enums;

public enum StatusEnum {

    UNSPIDER("unspider", 0), SUCCESS("spider_success", 1),
    NO_TAOBAO_PRODUCT("no_result", 2),TAOBAO_PRODUCT_CHECK_FAILED("check_failed", 3);

    private StatusEnum(String code, int value) {
        this.code = code;
        this.value = value;
    }

    public static StatusEnum getValueByCode(String code) {
        StatusEnum[] values = StatusEnum.values();
        for (int i = 0; i < values.length; i++) {
            StatusEnum status = values[i];
            if (status.code.toUpperCase().equals(code.toUpperCase())) {
                return status;
            }
        }
        return null;
    };

    public static StatusEnum getStatusByValue(int value) {
        StatusEnum[] values = StatusEnum.values();
        for (int i = 0; i < values.length; i++) {
            StatusEnum status = values[i];
            if (status.value == value) {
                return status;
            }
        }
        return null;
    };

    private String code;
    private int value;

    public String getCode() {
        return code;
    }

    public int getValue() {
        return value;
    }


}

