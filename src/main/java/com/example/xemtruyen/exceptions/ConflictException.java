package com.example.xemtruyen.exceptions;

import com.example.xemtruyen.constant.Constant;

public class ConflictException extends BaseException {
    public ConflictException(String message) {
        setCode(Constant.CodeValue.CONFLICT);
        setMessage(message);
    }
}
