package com.zero.official.accounts.enums;

/**
 * 状态码
 *
 * @author yezhaoxing
 * @date 2017/4/29
 */
public enum CodeEnum implements StringEnum {

    PARAM_NOT_MATCH("400"),

    PAGE_NOT_FOUND("404"),

    REQUEST_METHOD_NOT_ALLOW("405"),

    INTERNAL_SERVER_ERROR("500"),

    SUCCESS("000000"),

    FAIL("999999");

    private String CodeEnum;

    private CodeEnum(String value) {
        this.CodeEnum = value;
    }

    public String getCodeEnum() {
        return CodeEnum;
    }

}
