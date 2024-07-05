package com.sparta.everydrink.domain.liked.repository;

import com.sparta.everydrink.domain.liked.entity.ContentsTypeEnum;
import com.sparta.everydrink.domain.liked.entity.Liked;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public interface LikedRepository extends JpaRepository<Liked, Long>, LikedRepositoryCustom {
    Optional<Liked> findByUserIdAndContentsIdAndContentsType(Long user_id, Long contentsId, ContentsTypeEnum contentsType);
}
