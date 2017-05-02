package com.vitrenko.doyouknowthis.testcommons;

import com.vitrenko.doyouknowthis.domain.entity.Answer;
import com.vitrenko.doyouknowthis.domain.entity.Question;
import com.vitrenko.doyouknowthis.domain.entity.User;
import com.vitrenko.doyouknowthis.domain.entity.UserActivity;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

@Component
@Scope(SCOPE_PROTOTYPE)
public class EntityFactory {

    private int loginIncrement = 1;

    public User user() {
        String login = userLogin();
        String email = login + "@email.com";
        return new User(null, login, "password", email, 50);
    }

    public List<User> users(int count) {
        return IntStream.range(0, count).
                mapToObj(i -> user())
                .collect(Collectors.toList());
    }

    public String userLogin() {
        return "login" + loginIncrement++;
    }

    public Question question(User user) {
        return new Question(null, "question body", "question header", 30, userActivity(user));
    }

    public Question question() {
        return question(user());
    }

    public Answer answer(Question question, User user) {
        return new Answer(null, "answer body", question, 0, userActivity(user));
    }

    public Answer answer() {
        return answer(question(), user());
    }

    public UserActivity userActivity(User user) {
        return new UserActivity(user, LocalDateTime.now(), null);
    }

}
