package com.vitrenko.doyouknowthis.domain.repository;

import com.vitrenko.doyouknowthis.domain.entity.User;
import com.vitrenko.doyouknowthis.testcommons.IntegrationTest;
import org.junit.Before;
import org.junit.Test;

import javax.inject.Inject;

import static org.junit.Assert.*;

public class UserRepositoryIntegrationTest extends IntegrationTest {

    private static final String NOT_EXISTED_LOGIN = "NOT_EXISTED_LOGIN";

    private static final String NOT_EXISTED_EMAIL = "NOT_EXISTED_EMAIL@email.com";

    @Inject
    private UserRepository userRepository;

    private User persistentUser = entities().user();

    @Before
    public void setUp() {
        persist(persistentUser);
    }

    @Test
    public void shouldReturnUserIfLoginExists() {
        User foundUser = userRepository.findByLogin(persistentUser.getLogin());

        assertEquals(persistentUser, foundUser);
    }

    @Test
    public void shouldReturnNullIfLoginNotExist() {
        User foundUser = userRepository.findByLogin(NOT_EXISTED_LOGIN);

        assertNull(foundUser);
    }

    @Test
    public void shouldReturnTrueIfLoginExists() {
        assertTrue(
                userRepository.existsByLoginOrEmail(persistentUser.getLogin(), NOT_EXISTED_EMAIL)
        );
    }

    @Test
    public void shouldReturnTrueIfEmailExists() {
        assertTrue(
                userRepository.existsByLoginOrEmail(NOT_EXISTED_LOGIN, persistentUser.getEmail())
        );
    }

    @Test
    public void shouldReturnTrueIfLoginAndEmailExist() {
        assertTrue(
                userRepository.existsByLoginOrEmail(persistentUser.getLogin(), persistentUser.getEmail())
        );
    }

    @Test
    public void shouldReturnFalseIfLoginAndEmailNotExist() {
        assertFalse(
                userRepository.existsByLoginOrEmail(NOT_EXISTED_LOGIN, NOT_EXISTED_EMAIL)
        );
    }

}
