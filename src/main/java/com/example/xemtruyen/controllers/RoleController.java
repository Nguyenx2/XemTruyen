package com.example.xemtruyen.controllers;

import com.example.xemtruyen.models.Role;
import com.example.xemtruyen.reponses.ApiResponse;
import com.example.xemtruyen.services.message.MessageService;
import com.example.xemtruyen.services.role.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.example.xemtruyen.constant.Constant.CodeValue.SUCCESS;
import static com.example.xemtruyen.constant.Constant.CommonConstants.*;

@RestController("${api.prefix}/roles")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;
    private final MessageService messageService;

    @GetMapping
    ApiResponse<List<Role>> get(
            @RequestHeader(name = LANGUAGE, defaultValue = DEFAULT_LANGUAGE) String language
    ) {
        return ApiResponse.<List<Role>>builder()
                .code(SUCCESS)
                .message(messageService.getMessage(SUCCESSFULLY, language))
                .data(roleService.get())
                .build();
    }
}
