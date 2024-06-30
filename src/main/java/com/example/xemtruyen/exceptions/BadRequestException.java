package com.example.xemtruyen.exceptions;

import com.example.xemtruyen.constant.Constant;

public class BadRequestException extends BaseException {
    public BadRequestException(String message) {
        setCode(Constant.CodeValue.BAD_REQUEST);
        setMessage(message);
    }
}
