package me.ouohoon.basicreview.post.repository;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import me.ouohoon.basicreview.post.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

import static me.ouohoon.basicreview.post.domain.QPost.post;


@Repository
@RequiredArgsConstructor
public class PostRepositoryCustomImpl implements PostRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    public Page<Post> findAllWithQueryDsl(Pageable pageable) {
        JPAQuery<Post> query = queryFactory.select(post)
                .from(post)
                .leftJoin(post.comments)
                .distinct();

        for (Order order : pageable.getSort()) {
            PathBuilder pathBuilder = new PathBuilder(post.getType(), post.getMetadata());
            query.orderBy(new OrderSpecifier(order.isAscending() ?
                    com.querydsl.core.types.Order.ASC : com.querydsl.core.types.Order.DESC,
                    pathBuilder.get(order.getProperty())
            ));
        }

        List<Post> posts = query
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long totalSize = queryFactory.select(Wildcard.count)
                .from(post)
                .leftJoin(post.comments)
                .distinct()
                .fetchOne();

        return PageableExecutionUtils.getPage(posts, pageable, () -> totalSize);
    }
}
