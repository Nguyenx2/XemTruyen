package com.example.xemtruyen.utils;

import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class MapperUtil {
    private static final ModelMapper MODEL_MAPPER = new ModelMapper();
    private MapperUtil(){}

    public static <T, R>List<R> toDTOS(List<T> models, Class<R> responseClass) {
        return models
                .stream()
                .map(model -> MODEL_MAPPER.map(model, responseClass))
                .collect(Collectors.toList());
    }

    public static <T, R> R toResponse(T model, Class<R> responseClass) {
        return MODEL_MAPPER.map(model, responseClass);
    }

    public static <T, R> R toModel(T dto, Class<R> modelClass) {
        return MODEL_MAPPER.map(dto, modelClass);
    }
}
