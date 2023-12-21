package me.ouohoon.basicreview.user.service;

import me.ouohoon.basicreview.user.request.SignUpRequest;
import me.ouohoon.basicreview.user.response.UserResponse;

public interface UserService {

    UserResponse getUser(String nickname);

    UserResponse signUp(SignUpRequest request);

    void sendAuthEmail(String email);


}
