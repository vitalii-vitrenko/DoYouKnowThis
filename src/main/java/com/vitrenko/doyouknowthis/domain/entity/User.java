package com.vitrenko.doyouknowthis.domain.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Email;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude = "comments", callSuper = false)
@ToString(exclude = "comments", callSuper = true)
@Entity
@Table(name = "Client")
@Accessors(chain = true)
public class User extends DomainEntity {

    public static final int LOGIN_MIN_LENGTH = 3;

    private static final int LOGIN_MAX_LENGTH = 30;

    private static final int EMAIL_MAX_LENGTH = 50;

    public User(Long id, String login, String password, String email, int coins) {
        super(id);
        this.login = login;
        this.password = password;
        this.email = email;
        this.coins = coins;
    }

    @NotNull
    @Size(min = LOGIN_MIN_LENGTH, max = LOGIN_MAX_LENGTH)
    @Column(length = LOGIN_MAX_LENGTH, unique = true, nullable = false)
    private String login;

    @NotNull
    @Column(nullable = false)
    private String password;

    @Email
    @Size(max = EMAIL_MAX_LENGTH)
    @Column(length = EMAIL_MAX_LENGTH, unique = true, nullable = false)
    private String email;

    @Min(0)
    @Column(nullable = false)
    private int coins;

    @OneToMany(cascade = ALL, mappedBy = "userActivity.user", orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

}
