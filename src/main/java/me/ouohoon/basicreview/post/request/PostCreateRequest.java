package me.ouohoon.basicreview.post.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostCreateRequest {

    private String title;

    private String content;
}
