package com.vitrenko.doyouknowthis.domain.service;

import com.vitrenko.doyouknowthis.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class UserAlreadyExistException extends RuntimeException {

    @Getter
    private final User user;

}
