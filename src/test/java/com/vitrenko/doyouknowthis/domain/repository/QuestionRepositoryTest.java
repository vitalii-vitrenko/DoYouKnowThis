package com.vitrenko.doyouknowthis.domain.repository;

import com.vitrenko.doyouknowthis.domain.entity.Question;
import com.vitrenko.doyouknowthis.domain.entity.User;
import com.vitrenko.doyouknowthis.domain.entity.UserActivity;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.transaction.TestTransaction;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Transactional
public class QuestionRepositoryTest extends DatabaseAwareTest {

    private final User user = new User(
            null,
            "login",
            "password",
            "email@mail.com",
            50);

    @Inject
    private QuestionRepository questionRepository;

    @Inject
    private UserRepository userRepository;



    @Before

    public void setUp() {

    }


    @Test
    public void shouldReturnSavedEntity() {
        User savedUser = userRepository.saveAndFlush(user);
        long id = savedUser.getId();
        endTransaction();

        User gotUser = userRepository.getOne(savedUser.getId());
        Question question = question(gotUser);
        questionRepository.saveAndFlush(question);
        endTransaction();

        question = question(gotUser);
        questionRepository.saveAndFlush(question);
        endTransaction();

        question = question(gotUser);
        questionRepository.saveAndFlush(question);
        endTransaction();

        System.out.println("before find user");
        gotUser = userRepository.findOne(id);
        System.out.println(gotUser.getPosts());
    }

    public void endTransaction() {
        TestTransaction.flagForCommit();
        TestTransaction.end();
        TestTransaction.start();
    }


    public Question question(User user) {
        return new Question(null, "Question body", "Question header", 30, userActivity(user));
    }

    public UserActivity userActivity(User user) {
        return new UserActivity(user, LocalDateTime.now(), null);
    }

}
