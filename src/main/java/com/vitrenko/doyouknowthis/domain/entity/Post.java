package com.vitrenko.doyouknowthis.domain.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(exclude = {"comments"}, callSuper = false)
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Post extends DomainEntity {

    private static final int POST_BODY_MAX_SIZE = 5000;

    public Post(Long id, String body, UserActivity userActivity) {
        super(id);
        this.body = body;
        this.userActivity = userActivity;
    }

    @NotNull
    @Size(max = POST_BODY_MAX_SIZE)
    @Column(nullable = false, length = POST_BODY_MAX_SIZE)
    private String body;

    @Embedded
    private UserActivity userActivity;

    @OneToMany(mappedBy = "post", cascade = ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

}
