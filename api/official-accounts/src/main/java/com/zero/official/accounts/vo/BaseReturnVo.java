package com.zero.official.accounts.vo;

import com.zero.official.accounts.enums.CodeEnum;
import com.zero.official.accounts.enums.StringEnum;
import lombok.Getter;

/**
 * @author yezhaoxing
 * @date : 2017/4/17
 */
@Getter
public class BaseReturnVo {

    final static String SUCCESS_DEFAULT_DESC = "success";
    private String resCode;
    private String resDes;

    public BaseReturnVo(StringEnum codeEnum, String msg) {
        this.resCode = codeEnum.getCodeEnum();
        this.resDes = msg;
    }

    public static BaseReturnVo success() {
        return new BaseReturnVo(CodeEnum.SUCCESS, SUCCESS_DEFAULT_DESC);
    }
}
