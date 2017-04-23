package com.vitrenko.doyouknowthis.domain.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
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
@EqualsAndHashCode(exclude = {"comments", "posts"}, callSuper = false)
@ToString(exclude = {"comments", "posts"}, callSuper = true)
@Entity
@Table(name = "client")
public class User extends DomainEntity {

    public User(Long id, String login, String password, String email, int coins) {
        super(id);
        this.login = login;
        this.password = password;
        this.email = email;
        this.coins = coins;
    }

    @NotNull
    @Size(min = 3, max = 25)
    private String login;

    @NotNull
    @Size(min = 6, max = 30)
    private String password;

    @Email
    @Column(length = 50)
    private String email;

    @Min(0)
    @Column(nullable = false)
    private int coins;

    @OneToMany(cascade = ALL, mappedBy = "userActivity.user", orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(cascade = ALL, mappedBy = "userActivity.user", orphanRemoval = true)
    private List<Post> posts = new ArrayList<>();

}
