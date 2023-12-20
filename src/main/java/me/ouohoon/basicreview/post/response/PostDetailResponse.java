package me.ouohoon.basicreview.post.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.ouohoon.basicreview.comment.response.CommentResponse;
import me.ouohoon.basicreview.post.domain.Post;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostDetailResponse {

    private String title;

    private String content;

    private String nickname;

    private List<CommentResponse> comments;

    private LocalDateTime createdAt;

    public PostDetailResponse(Post post) {
        this.title = post.getTitle();
        this.content = post.getContent();
        this.nickname = post.getUser().getNickname();
        this.comments = post.getComments().stream()
                .map(CommentResponse::new).toList();
        this.createdAt = post.getCreatedAt();
    }
}
