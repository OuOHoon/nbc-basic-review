package me.ouohoon.basicreview.post.service;

import lombok.RequiredArgsConstructor;
import me.ouohoon.basicreview.post.domain.Post;
import me.ouohoon.basicreview.post.repository.PostRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PostScheduler {

    private final PostRepository postRepository;

    private static final int DEADLINE = 90;

    @Scheduled(cron = "0 0 0 * * *") // 매일 0시에 90일보다 오래된 게시글 삭제
    @Transactional
    public void removeOldData() {
        LocalDateTime ago90day = LocalDateTime.now().minusDays(DEADLINE);
        List<Post> oldPosts = postRepository.findAllByModifiedAtLessThan(ago90day);
        postRepository.deleteAll(oldPosts);
    }
}
