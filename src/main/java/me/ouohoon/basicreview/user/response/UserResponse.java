package me.ouohoon.basicreview.user.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.ouohoon.basicreview.user.domain.User;

@NoArgsConstructor
@Getter
public class UserResponse {

    private Long id;

    private String nickname;

    public UserResponse(User user) {
        this.id = user.getId();
        this.nickname = user.getNickname();
    }
}
