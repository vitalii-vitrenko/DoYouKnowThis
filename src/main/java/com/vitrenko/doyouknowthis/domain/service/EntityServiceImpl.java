package com.vitrenko.doyouknowthis.domain.service;

import com.vitrenko.doyouknowthis.domain.entity.DomainEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Objects;

abstract class EntityServiceImpl<T extends DomainEntity> implements EntityService<T> {

    JpaRepository<T, Long> repository;

    public EntityServiceImpl(JpaRepository<T, Long> repository) {
        this.repository = Objects.requireNonNull(repository);
    }

    @Override
    public T find(long id) {
        return repository.findOne(id);
    }

    @Override
    public List<T> findAll() {
        return repository.findAll();
    }

    @Override
    public T save(T entity) {
        return repository.saveAndFlush(entity);
    }

    @Override
    public void delete(long id) {
        repository.delete(id);
    }

    @Override
    public Page<T> find(Pageable pageable) {
        return repository.findAll(pageable);
    }
}
