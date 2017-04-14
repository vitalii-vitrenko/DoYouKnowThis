package com.vitrenko.doyouknowthis.domain.repository;

import com.vitrenko.doyouknowthis.domain.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {
}
