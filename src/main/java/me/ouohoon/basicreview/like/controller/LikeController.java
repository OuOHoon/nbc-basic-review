package me.ouohoon.basicreview.like.controller;

import lombok.RequiredArgsConstructor;
import me.ouohoon.basicreview.global.dto.BaseResponse;
import me.ouohoon.basicreview.like.service.LikeService;
import me.ouohoon.basicreview.security.auth.jwt.UserDetailsImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/posts/{postId}/likes")
public class LikeController {

    private final LikeService likeService;

    @PostMapping
    public ResponseEntity<BaseResponse<String>> like(
            @PathVariable Long postId,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        likeService.like(postId, userDetails.getUser());
        return ResponseEntity.ok(BaseResponse.success(""));
    }

    @DeleteMapping
    public ResponseEntity<BaseResponse<Integer>> unlike(
            @PathVariable Long postId,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        likeService.unlike(postId, userDetails.getUser());
        return ResponseEntity.noContent().build();
    }
}
