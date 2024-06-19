package com.example.xemtruyen.services.message;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {
    private final MessageSource messageSource;

    @Override
    public String getMessage(String code, String language) {
        try {
            return messageSource.getMessage(code, null, new Locale(language));
        } catch (Exception e) {
            return code;
        }
    }
}
