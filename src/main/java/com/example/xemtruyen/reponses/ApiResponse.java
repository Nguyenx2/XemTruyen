package com.example.xemtruyen.reponses;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class ApiResponse <T>{
    private int code;
    private String message;
    private T data;

    public static <T> ApiResponse<T> ofSuccess(String message, T data) {
        return of(HttpStatus.OK.value(), message, data);
    }

    public static <T> ApiResponse<T> ofCreated(String message, T data) {
        return of(HttpStatus.CREATED.value(), message, data);
    }
}
