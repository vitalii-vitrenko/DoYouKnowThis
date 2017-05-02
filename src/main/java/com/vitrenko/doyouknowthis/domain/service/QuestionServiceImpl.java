package com.vitrenko.doyouknowthis.domain.service;

import com.vitrenko.doyouknowthis.domain.entity.Question;
import com.vitrenko.doyouknowthis.domain.repository.QuestionRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Objects;

@Service
@Transactional
public class QuestionServiceImpl extends PostServiceImpl<Question> implements QuestionService {

    private final QuestionRepository questionRepository;

    @Inject
    public QuestionServiceImpl(QuestionRepository questionRepository) {
        super(questionRepository);
        this.questionRepository = Objects.requireNonNull(questionRepository);
    }
}
