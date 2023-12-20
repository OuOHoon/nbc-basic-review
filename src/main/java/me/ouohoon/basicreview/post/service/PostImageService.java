package me.ouohoon.basicreview.post.service;

import lombok.RequiredArgsConstructor;
import me.ouohoon.basicreview.global.exception.BaseException;
import me.ouohoon.basicreview.global.exception.ErrorCode;
import me.ouohoon.basicreview.post.domain.Post;
import me.ouohoon.basicreview.post.domain.PostImage;
import me.ouohoon.basicreview.post.repository.PostImageRepository;
import me.ouohoon.basicreview.s3.S3Manager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class PostImageService {

    private final PostImageRepository postImageRepository;
    private final S3Manager s3Manager;

    @Transactional
    public void uploadImage(Post post, MultipartFile image) {
        try {
            String imageUrl = s3Manager.uploadMultipartFileWithPublicRead(image);
            PostImage postImage = new PostImage(imageUrl);
            postImageRepository.save(postImage);
            post.addImage(postImage);
        } catch (Exception e) {
            throw new BaseException(ErrorCode.FAIL_TO_IMAGE_UPLOAD);
        }
    }
}
