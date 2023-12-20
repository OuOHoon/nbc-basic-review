package me.ouohoon.basicreview.post.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import me.ouohoon.basicreview.post.domain.Post;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class PostFindAllResponse {

    private String title;

    private String nickname;

    private LocalDateTime createdAt;

    public PostFindAllResponse(Post post) {
        this.title = post.getTitle();
        this.nickname = post.getUser().getNickname();
        this.createdAt = post.getCreatedAt();
    }
}
