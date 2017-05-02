package com.vitrenko.doyouknowthis.domain.service;

import com.vitrenko.doyouknowthis.domain.entity.User;
import com.vitrenko.doyouknowthis.domain.repository.UserRepository;
import com.vitrenko.doyouknowthis.testcommons.EntityFactory;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.dao.DuplicateKeyException;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;

public class UserServiceTest {

    private UserRepository userRepository = mock(UserRepository.class);

    private UserService userService = new UserServiceImpl(userRepository);


    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private EntityFactory entities = new EntityFactory();

    private User unregisteredUser = entities.user();


    @Test
    public void shouldSaveUser() {
        userService.save(unregisteredUser);

        then(userRepository).should().saveAndFlush(unregisteredUser);
    }

    @Test
    public void shouldThrowExceptionIfLoginOrEmailAlreadyExist() {
        given(userRepository.saveAndFlush(unregisteredUser)).willThrow(DuplicateKeyException.class);

        thrown.expect(UserAlreadyExistException.class);
        thrown.expect(hasProperty("user", equalTo(unregisteredUser)));

        userService.save(unregisteredUser);
    }

    @Test
    public void shouldReturnSavedUser() {
        User registeredUser = mock(User.class);
        given(userRepository.saveAndFlush(unregisteredUser)).willReturn(registeredUser);

        User receivedUser = userService.save(unregisteredUser);

        assertEquals(registeredUser, receivedUser);
    }
}
