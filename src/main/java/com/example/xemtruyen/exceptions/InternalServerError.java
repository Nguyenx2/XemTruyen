package com.example.xemtruyen.exceptions;

import com.example.xemtruyen.constant.Constant;

public class InternalServerError extends BaseException {
    public InternalServerError(String message) {
        setCode(Constant.CodeException.INTERNAL_SERVER_ERROR);
        setMessage(message);
    }
}
