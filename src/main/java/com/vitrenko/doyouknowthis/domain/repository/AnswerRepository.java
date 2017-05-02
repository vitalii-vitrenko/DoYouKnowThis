package com.vitrenko.doyouknowthis.domain.repository;

import com.vitrenko.doyouknowthis.domain.entity.Answer;
import org.springframework.data.jpa.repository.EntityGraph;

import java.util.List;

public interface AnswerRepository extends PostRepository<Answer> {

}
