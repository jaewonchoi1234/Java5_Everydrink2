package com.sparta.everydrink.domain.liked.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.everydrink.domain.comment.entity.Comment;
import com.sparta.everydrink.domain.comment.entity.QComment;
import com.sparta.everydrink.domain.liked.entity.ContentsTypeEnum;
import com.sparta.everydrink.domain.liked.entity.Liked;
import com.sparta.everydrink.domain.post.entity.Post;
import com.sparta.everydrink.domain.post.entity.QPost;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;


@Repository
@RequiredArgsConstructor
public class LikeRepositoryImpl implements LikedRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;


    @Override
    public List<Post> getLikedPosts(Long userId, Pageable pageable){
        QPost post = QPost.post;

        return jpaQueryFactory.select(post)
                .from(post)
                .where(post.user.id.eq(userId))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

    }

    @Override
    public List<Comment> getLikedComments(Long userId, Pageable pageable){
        QComment comment = QComment.comment;

        return jpaQueryFactory.select(comment)
                .from(comment)
                .where(comment.user.id.eq(userId))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

}
