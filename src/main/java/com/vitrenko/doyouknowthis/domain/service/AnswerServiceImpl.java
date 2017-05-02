package com.vitrenko.doyouknowthis.domain.service;

import com.vitrenko.doyouknowthis.domain.entity.Answer;
import com.vitrenko.doyouknowthis.domain.repository.AnswerRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Objects;

@Service
@Transactional
public class AnswerServiceImpl extends PostServiceImpl<Answer> implements AnswerService {

    private final AnswerRepository answerRepository;

    @Inject
    public AnswerServiceImpl(AnswerRepository answerRepository) {
        super(answerRepository);
        this.answerRepository = Objects.requireNonNull(answerRepository);
    }
}
