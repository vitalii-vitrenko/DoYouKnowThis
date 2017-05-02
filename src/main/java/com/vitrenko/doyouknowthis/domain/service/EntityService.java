package com.vitrenko.doyouknowthis.domain.service;

import com.vitrenko.doyouknowthis.domain.entity.DomainEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EntityService<T extends DomainEntity> {

    T find(long id);

    List<T> findAll();

    T save(T entity);

    void delete(long id);

    Page<T> find(Pageable pageable);
}
