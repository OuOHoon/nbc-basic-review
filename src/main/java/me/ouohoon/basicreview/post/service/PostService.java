package me.ouohoon.basicreview.post.service;

import lombok.RequiredArgsConstructor;
import me.ouohoon.basicreview.global.dto.SortOption;
import me.ouohoon.basicreview.global.exception.BaseException;
import me.ouohoon.basicreview.global.exception.ErrorCode;
import me.ouohoon.basicreview.post.domain.Post;
import me.ouohoon.basicreview.post.repository.PostRepository;
import me.ouohoon.basicreview.post.request.PostCreateRequest;
import me.ouohoon.basicreview.post.request.PostListRequest;
import me.ouohoon.basicreview.post.request.PostUpdateRequest;
import me.ouohoon.basicreview.post.response.PostDetailResponse;
import me.ouohoon.basicreview.post.response.PostListResponse;
import me.ouohoon.basicreview.user.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

    private final PostRepository postRepository;
    private final PostImageService postImageService;

    public List<PostListResponse> findAll(PostListRequest request) {
        List<SortOption> sortOptions = request.getSortOptions();
        List<Order> orders = sortOptions.stream().map((option) ->
                new Order(
                        Direction.fromOptionalString(option.getOrder())
                                .orElseThrow(() -> new BaseException(ErrorCode.INVALID_SORT_DIRECTION)),
                        option.getKey()
                )
        ).toList();
        Sort sort = Sort.by(orders);

        Pageable pageable = PageRequest.of(request.getPage(), request.getSize(), sort);
        Page<Post> posts = postRepository.findAll(pageable);
        return posts.stream().map(PostListResponse::new).toList();
    }

    public PostDetailResponse findById(Long id) {
        return new PostDetailResponse(
                postRepository.findById(id)
                        .orElseThrow(() -> new BaseException(ErrorCode.NOT_EXIST_POST))
        );
    }

    @Transactional
    public PostDetailResponse post(PostCreateRequest request, User user, List<MultipartFile> images) {
        Post post = new Post(request);
        post.setUser(user);
        Post savedPost = postRepository.save(post);
        for (MultipartFile image : images) {
            postImageService.uploadImage(savedPost, image);
        }
        return new PostDetailResponse(savedPost);
    }

    @Transactional
    public PostDetailResponse update(Long postId, PostUpdateRequest request, User user) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new BaseException(ErrorCode.NOT_EXIST_POST));

        if (!post.getUser().getId().equals(user.getId())) {
            throw new BaseException(ErrorCode.PERMISSION_DENIED);
        }

        post.update(request);
        return new PostDetailResponse(post);
    }

    @Transactional
    public void delete(Long postId, User user) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new BaseException(ErrorCode.NOT_EXIST_POST));

        if (!post.getUser().getId().equals(user.getId())) {
            throw new BaseException(ErrorCode.PERMISSION_DENIED);
        }

        postRepository.delete(post);
    }
}
