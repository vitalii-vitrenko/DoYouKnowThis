package com.vitrenko.doyouknowthis.domain.service;

import com.vitrenko.doyouknowthis.domain.entity.User;
import com.vitrenko.doyouknowthis.domain.repository.UserRepository;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Objects;

@Service
@Transactional
public class UserServiceImpl extends EntityServiceImpl<User> implements UserService {

    private final UserRepository userRepository;

    @Inject
    public UserServiceImpl(UserRepository userRepository) {
        super(userRepository);
        this.userRepository = Objects.requireNonNull(userRepository);
    }

    @Override
    public User save(User user) {
        if (exists(user)) {
            throw new UserAlreadyExistException(user);
        }

        try {
            return userRepository.saveAndFlush(user);
        } catch (DuplicateKeyException ex) {
            throw new UserAlreadyExistException(user);
        }
    }


    @Override
    public boolean exists(User user) {
        return userRepository.existsByLoginOrEmail(user.getLogin(), user.getEmail());
    }
}
