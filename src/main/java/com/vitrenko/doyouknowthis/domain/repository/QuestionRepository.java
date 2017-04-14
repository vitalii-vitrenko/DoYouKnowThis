package com.vitrenko.doyouknowthis.domain.repository;

import com.vitrenko.doyouknowthis.domain.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
}
