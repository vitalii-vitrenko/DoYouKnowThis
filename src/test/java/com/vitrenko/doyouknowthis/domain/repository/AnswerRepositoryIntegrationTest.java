package com.vitrenko.doyouknowthis.domain.repository;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.vitrenko.doyouknowthis.domain.entity.Answer;
import com.vitrenko.doyouknowthis.domain.entity.Question;
import com.vitrenko.doyouknowthis.domain.entity.User;
import com.vitrenko.doyouknowthis.testcommons.IntegrationTest;
import org.junit.Before;
import org.junit.Test;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;


public class AnswerRepositoryIntegrationTest extends IntegrationTest {

    @Inject
    private AnswerRepository answerRepository;

    private User userWithAnswers = entities().user();


    @Before
    public void setUp() {
        persist(userWithAnswers);
    }

    @Test
    @DatabaseSetup("/testData.xml")
    public void shouldReturnAnswersOnlyFromGivenUser() {
        List<Answer> answers = persistAnswersForUser(userWithAnswers, 3);

        List<Answer> foundAnswers = answerRepository.findByUserId(userWithAnswers.getId());

        assertThat(answers, containsInAnyOrder(foundAnswers.toArray()));
    }

    @Test
    @DatabaseSetup("/testData.xml")
    public void shouldReturnEmptyListIfNoQuestionsForGivenUser() {
        List<Answer> foundAnswers = answerRepository.findByUserId(userWithAnswers.getId());

        assertTrue(foundAnswers.isEmpty());
    }

    private List<Answer> persistAnswersForUser(User user, int count) {
        User askedUser = entities().user();
        persist(askedUser);
        List<Answer> answers = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            Question question = entities().question(askedUser);
            Answer answer = entities().answer(question, user);
            persist(answer.getQuestion());
            persist(answer);
            answers.add(answer);
        }
        return answers;
    }

}
