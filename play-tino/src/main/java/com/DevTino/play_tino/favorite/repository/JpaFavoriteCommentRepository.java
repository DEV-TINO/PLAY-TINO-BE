package com.DevTino.play_tino.favorite.repository;

import com.DevTino.play_tino.favorite.domain.FavoriteComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface JpaFavoriteCommentRepository extends JpaRepository<FavoriteComment, UUID> {

    //정렬해서 전체 comment 찾기(기준 : uploadTime 내림차순[최신순])
    List<FavoriteComment> findAllByOrderByUploadTimeDesc();

    //정렬해서 전체 comment 찾기(기준 : heartCount 내림차순[최신순])
    List<FavoriteComment> findAllByOrderByHeartCountDescUploadTimeDesc();

    // 페이징해서 comment 찾기
    Page<FavoriteComment> findAll(Pageable pageable);

    // heartCount 기준 페이징
    Page<FavoriteComment> findAllByOrderByHeartCountDescUploadTimeDesc(Pageable pageable);

    // uploadTime 기준 페이징
    Page<FavoriteComment> findAllByOrderByUploadTimeDesc(Pageable pageable);
}
