package com.example.xemtruyen.services.role;

import com.example.xemtruyen.models.Role;

import java.util.List;

public interface RoleService {
    List<Role> get();
    Role findByName(String name);
}
