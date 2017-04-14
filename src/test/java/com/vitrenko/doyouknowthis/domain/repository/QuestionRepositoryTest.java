package com.vitrenko.doyouknowthis.domain.repository;

import com.vitrenko.doyouknowthis.domain.entity.Question;
import com.vitrenko.doyouknowthis.domain.entity.User;
import org.junit.Before;
import org.junit.Test;

import javax.inject.Inject;
import java.util.Collections;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

public class QuestionRepositoryTest extends DatabaseAwareTest {

    private final User user = mock(User.class);

    private final Question question = new Question(
            1L,
            "Question",
            "Question body",
            user,
            Collections.emptyList(),
            Collections.emptyList()
    );

    @Inject
    private QuestionRepository repository;

    private Question savedQuestion;

    @Before
    public void setUp() {
        savedQuestion = repository.saveAndFlush(question);
    }

    @Test
    public void shouldReturnSavedEntity() {
        assertNotNull("Saved entity must be not null", savedQuestion);
        assertNotNull("Id of saved entity must be not null", savedQuestion.getId());
    }

    @Test
    public void shouldFindEntity() {
        Question foundQuestion = repository.findOne(savedQuestion.getId());
        assertTrue("Found entity should be equals to saved", question.equals(foundQuestion));
    }

}
