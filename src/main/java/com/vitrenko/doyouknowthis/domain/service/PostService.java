package com.vitrenko.doyouknowthis.domain.service;

import com.vitrenko.doyouknowthis.domain.entity.Post;

import java.util.List;

public interface PostService<T extends Post>  extends EntityService<T> {

    List<T> findByUserId(long id);
}
