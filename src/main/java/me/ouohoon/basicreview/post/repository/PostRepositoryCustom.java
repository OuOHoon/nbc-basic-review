package me.ouohoon.basicreview.post.repository;

import me.ouohoon.basicreview.post.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostRepositoryCustom {

    Page<Post> findAllWithQueryDsl(Pageable pageable);
}
