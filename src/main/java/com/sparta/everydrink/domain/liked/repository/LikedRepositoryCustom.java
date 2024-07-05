package com.sparta.everydrink.domain.liked.repository;

import com.sparta.everydrink.domain.comment.entity.Comment;
import com.sparta.everydrink.domain.liked.entity.ContentsTypeEnum;
import com.sparta.everydrink.domain.post.entity.Post;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;


@Repository
public interface LikedRepositoryCustom {

    List<Post> getLikedPosts(Long userId,Pageable pageable);

    List<Comment> getLikedComments(Long userId,Pageable pageable);

}
