package me.ouohoon.basicreview.post.controller;

import lombok.RequiredArgsConstructor;
import me.ouohoon.basicreview.global.dto.BaseResponse;
import me.ouohoon.basicreview.global.dto.SortOption;
import me.ouohoon.basicreview.post.request.PostCreateRequest;
import me.ouohoon.basicreview.post.request.PostListRequest;
import me.ouohoon.basicreview.post.request.PostUpdateRequest;
import me.ouohoon.basicreview.post.response.PostDetailResponse;
import me.ouohoon.basicreview.post.response.PostListResponse;
import me.ouohoon.basicreview.post.service.PostService;
import me.ouohoon.basicreview.security.auth.jwt.UserDetailsImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    @GetMapping
    public ResponseEntity<BaseResponse<List<PostListResponse>>> findAll(
            @RequestParam(defaultValue = "0", required = false) int page,
            @RequestParam(defaultValue = "5", required = false) int size,
            @RequestParam(required = false) List<String> sorts
    ) {
        PostListRequest request;
        if (sorts == null) {
            request = new PostListRequest(page, size);
        }
        else {
            List<SortOption> options = sorts.stream().map((sort) -> {
                String[] keyOption = sort.split(",");
                return new SortOption(keyOption[0], keyOption[1]);
            }).toList();
            request = new PostListRequest(page, size, options);
        }

        List<PostListResponse> posts = postService.findAll(request);
        return ResponseEntity.ok(BaseResponse.success(posts));
    }

    @GetMapping("/{postId}")
    public ResponseEntity<BaseResponse<PostDetailResponse>> findById(@PathVariable Long postId) {
        PostDetailResponse post = postService.findById(postId);
        return ResponseEntity.ok(BaseResponse.success(post));
    }

    @PostMapping
    public ResponseEntity<BaseResponse<PostDetailResponse>> post(
            @RequestPart("data") PostCreateRequest request,
            @RequestPart("images") List<MultipartFile> images,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        PostDetailResponse post = postService.post(request, userDetails.getUser(), images);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(BaseResponse.success(post));
    }

    @PutMapping("/{postId}")
    public ResponseEntity<BaseResponse<PostDetailResponse>> update(
            @PathVariable Long postId,
            @RequestBody PostUpdateRequest request,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        PostDetailResponse post = postService.update(postId, request, userDetails.getUser());
        return ResponseEntity.ok(BaseResponse.success(post));
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> delete(
            @PathVariable Long postId,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        postService.delete(postId, userDetails.getUser());
        return ResponseEntity.noContent().build();
    }
}
