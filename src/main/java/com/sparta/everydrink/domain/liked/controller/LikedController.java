package com.sparta.everydrink.domain.liked.controller;

import com.sparta.everydrink.domain.comment.dto.CommentResponseDto;
import com.sparta.everydrink.domain.common.CommonResponseDto;
import com.sparta.everydrink.domain.liked.dto.LikedRequestDto;
import com.sparta.everydrink.domain.liked.dto.LikedResponseDto;
import com.sparta.everydrink.domain.liked.entity.ContentsTypeEnum;
import com.sparta.everydrink.domain.liked.service.LikedService;
import com.sparta.everydrink.domain.post.dto.PostResponseDto;
import com.sparta.everydrink.security.UserDetailsImpl;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/like")
public class LikedController {

    private final LikedService likedService;

    @PostMapping
    public ResponseEntity<CommonResponseDto<LikedResponseDto>> addLike(
            @AuthenticationPrincipal UserDetailsImpl user,
            @RequestBody LikedRequestDto requestDto) {
        LikedResponseDto responseDto = likedService.addLike(requestDto, user);
        return ResponseEntity.ok().body(CommonResponseDto.<LikedResponseDto>builder()
                .statusCode(HttpStatus.OK.value()).message("좋아요 추가 성공").data(responseDto).build());
    }

    @DeleteMapping
    public ResponseEntity<CommonResponseDto<LikedResponseDto>> removeLike(
            @AuthenticationPrincipal UserDetailsImpl user,
            @RequestBody LikedRequestDto requestDto) {
        likedService.removeLike(requestDto, user);
        return ResponseEntity.ok().body(CommonResponseDto.<LikedResponseDto>builder()
                .statusCode(HttpStatus.OK.value()).message("좋아요 제거 성공").build());
    }


    @GetMapping("post")
    public ResponseEntity<List<PostResponseDto>> getLikePosts(
            @AuthenticationPrincipal UserDetailsImpl user, @RequestParam("page") int page,
            @RequestParam("size") int size) {
        List<PostResponseDto> resultData = likedService.getLikePosts(
                user.getUser().getId(), page, size);

        return ResponseEntity.ok().body(resultData);
    }


    @GetMapping("comment")
        public ResponseEntity<List<CommentResponseDto>> getLikeComments(
            @AuthenticationPrincipal UserDetailsImpl user, @RequestParam("page") int page,
            @RequestParam("size") int size) {
        List<CommentResponseDto> resultData = likedService.getLikeComments(
                user.getUser().getId(), page, size);

        return ResponseEntity.ok().body(resultData);
    }


}
