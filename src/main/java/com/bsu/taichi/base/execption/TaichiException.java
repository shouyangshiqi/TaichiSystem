package com.bsu.taichi.base.execption;

import lombok.Data;
import lombok.ToString;

/**
 * @author khran
 * @version 1.0
 * @description
 */

@Data
@ToString
public class TaichiException extends RuntimeException{

    private String errMessage;

    public TaichiException() {
        super();
    }

    public TaichiException(String errMessage) {
        super(errMessage);
        this.errMessage = errMessage;
    }

    public static void cast(CommonError commonError){
        throw new TaichiException(commonError.getErrMessage());
    }
    public static void cast(String errMessage){
        throw new TaichiException(errMessage);
    }
}
