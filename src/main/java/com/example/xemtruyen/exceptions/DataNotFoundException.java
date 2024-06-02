package com.example.xemtruyen.exceptions;

import com.example.xemtruyen.constant.Constant;

public class DataNotFoundException extends BaseException {
    public DataNotFoundException(String message) {
        setCode(Constant.CodeException.NOT_FOUND);
        setMessage(message);
    }
}
