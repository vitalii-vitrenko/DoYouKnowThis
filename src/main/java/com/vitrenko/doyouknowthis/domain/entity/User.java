package com.vitrenko.doyouknowthis.domain.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.Email;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

import static javax.persistence.CascadeType.REMOVE;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false, exclude = {"questions", "answers", "comments"})
@ToString(callSuper = true)
@Entity
public class User extends DomainEntity {

    @NotNull
    @Size(min = 3, max = 25)
    private String login;

    @NotNull
    @Size(min = 6, max = 30)
    private String password;

    @Email
    private String email;

    @OneToMany(mappedBy = "user", cascade = REMOVE, orphanRemoval = true)
    private List<Question> questions;

    @OneToMany(mappedBy = "user", cascade = REMOVE, orphanRemoval = true)
    private List<Answer> answers;

    @OneToMany(mappedBy = "user", cascade = REMOVE, orphanRemoval = true)
    private List<Comment> comments;

}
