package com.vitrenko.doyouknowthis.domain.service;

import com.vitrenko.doyouknowthis.domain.entity.User;

public interface UserService extends EntityService<User> {

    boolean exists(User user);

}
