package com.zero.official.accounts.vo;

import com.zero.official.accounts.enums.CodeEnum;
import com.zero.official.accounts.enums.StringEnum;
import lombok.Getter;
import lombok.Setter;

/**
 * @author yezhaoxing
 * @date : 2017/4/17
 */
@Getter
@Setter
public class ReturnVo<T> extends BaseReturnVo {

    private T data;

    public ReturnVo(StringEnum codeEnum, String msg) {
        super(codeEnum, msg);
    }

    public static <T> ReturnVo<T> success(T data) {
        ReturnVo<T> returnVo = new ReturnVo<>(CodeEnum.SUCCESS, SUCCESS_DEFAULT_DESC);
        returnVo.setData(data);
        return returnVo;
    }
}
