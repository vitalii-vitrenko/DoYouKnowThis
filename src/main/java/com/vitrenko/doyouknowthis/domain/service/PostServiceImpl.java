package com.vitrenko.doyouknowthis.domain.service;

import com.vitrenko.doyouknowthis.domain.entity.Post;
import com.vitrenko.doyouknowthis.domain.repository.PostRepository;

import java.util.List;
import java.util.Objects;

abstract class PostServiceImpl<T extends Post> extends EntityServiceImpl<T> implements PostService<T> {

    private final PostRepository<T> postRepository;

    public PostServiceImpl(PostRepository<T> postRepository) {
        super(postRepository);
        this.postRepository = Objects.requireNonNull(postRepository);
    }

    @Override
    public List<T> findByUserId(long id) {
        return postRepository.findByUserId(id);
    }
}
