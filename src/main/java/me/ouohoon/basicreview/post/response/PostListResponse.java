package me.ouohoon.basicreview.post.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import me.ouohoon.basicreview.post.domain.Post;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class PostListResponse {

    private String title;

    private String nickname;

    private LocalDateTime createdAt;

    public PostListResponse(Post post) {
        this.title = post.getTitle();
        this.nickname = post.getUser().getNickname();
        this.createdAt = post.getCreatedAt();
    }
}
