package com.example.xemtruyen.exceptions;

import com.example.xemtruyen.constant.Constant;

public class InternalServerError extends BaseException {
    public InternalServerError(String message) {
        setCode(Constant.CodeValue.INTERNAL_SERVER_ERROR);
        setMessage(message);
    }
}
