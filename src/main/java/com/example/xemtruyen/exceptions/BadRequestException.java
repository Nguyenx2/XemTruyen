package com.example.xemtruyen.exceptions;

import com.example.xemtruyen.constant.Constant;

public class BadRequestException extends BaseException {
    public BadRequestException(String message) {
        setCode(Constant.CodeException.BAD_REQUEST);
        setMessage(message);
    }
}
