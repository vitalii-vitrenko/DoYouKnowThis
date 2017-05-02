package com.vitrenko.doyouknowthis.domain.repository;

import com.vitrenko.doyouknowthis.domain.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface PostRepository<T extends Post> extends JpaRepository<T, Long> {

    @Query("SELECT p FROM #{#entityName} p WHERE p.userActivity.user.id = ?1")
    List<T> findByUserId(long id);
}
