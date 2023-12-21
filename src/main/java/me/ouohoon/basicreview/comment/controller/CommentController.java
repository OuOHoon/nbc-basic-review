package me.ouohoon.basicreview.comment.controller;

import lombok.RequiredArgsConstructor;
import me.ouohoon.basicreview.comment.request.CommentCreateRequest;
import me.ouohoon.basicreview.comment.request.CommentUpdateRequest;
import me.ouohoon.basicreview.comment.response.CommentResponse;
import me.ouohoon.basicreview.comment.service.CommentService;
import me.ouohoon.basicreview.comment.service.CommentServiceImpl;
import me.ouohoon.basicreview.global.dto.BaseResponse;
import me.ouohoon.basicreview.global.exception.BaseException;
import me.ouohoon.basicreview.global.exception.ErrorCode;
import me.ouohoon.basicreview.security.auth.jwt.UserDetailsImpl;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts/{postId}/comments")
public class CommentController {

    private final CommentService commentService;

    @GetMapping
    public ResponseEntity<BaseResponse<List<CommentResponse>>> findAll(
            @PathVariable Long postId,
            @RequestParam(defaultValue = "0", required = false) int page,
            @RequestParam(defaultValue = "5", required = false) int size,
            @RequestParam(required = false) List<String> sorts
    ) {
        List<Order> orders = new ArrayList<>();
        if (sorts != null) {
            orders = sorts.stream().map((sort) -> {
                String[] keyOption = sort.split(",");
                return new Order(
                        Sort.Direction.fromOptionalString(keyOption[1])
                                .orElseThrow(() -> new BaseException(ErrorCode.INVALID_SORT_DIRECTION)),
                        keyOption[0]
                );
            }).toList();
        }
        List<CommentResponse> comments = commentService.findAll(postId, page, size, orders);
        return ResponseEntity.ok(BaseResponse.success(comments));
    }

    @PostMapping
    public ResponseEntity<BaseResponse<CommentResponse>> comment(
            @PathVariable Long postId,
            @RequestBody CommentCreateRequest request,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        CommentResponse comment = commentService.comment(postId, userDetails.getUser(), request);
        return ResponseEntity.ok(BaseResponse.success(comment));
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<BaseResponse<CommentResponse>> update(
            @PathVariable Long postId,
            @PathVariable Long commentId,
            @RequestBody CommentUpdateRequest request,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        CommentResponse comment = commentService.update(postId, commentId, userDetails.getUser(), request);
        return ResponseEntity.ok(BaseResponse.success(comment));
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<BaseResponse<Void>> delete(
            @PathVariable Long postId,
            @PathVariable Long commentId,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        commentService.delete(postId, commentId, userDetails.getUser());
        return ResponseEntity.noContent().build();
    }
}
