package com.example.xemtruyen.exceptions;

import lombok.Data;

@Data
public class BaseException extends RuntimeException {
    private int code = 9999;
    private String message = "Uncategorized error";
}
