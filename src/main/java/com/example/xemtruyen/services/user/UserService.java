package com.example.xemtruyen.services.user;

import com.example.xemtruyen.dtos.user.ChangePasswordRequest;
import com.example.xemtruyen.dtos.user.UserUpdateRequest;
import com.example.xemtruyen.reponses.user.UserPageResponse;
import com.example.xemtruyen.reponses.user.UserResponse;

public interface UserService {

    UserResponse updateUser(long id, UserUpdateRequest request);

    UserPageResponse list(String keyword, int page, int size);

    void blockOrEnable(long id, boolean active);

    UserResponse details(long id);

    void delete(long id);

    void changePassword(long id, ChangePasswordRequest request);
}
