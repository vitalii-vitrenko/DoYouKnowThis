package com.vitrenko.doyouknowthis.domain.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static javax.persistence.FetchType.LAZY;

@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
@ToString(callSuper = true)
@Entity
public class Comment extends DomainEntity {

    private static final int BODY_MAX_SIZE = 200;

    public Comment(Long id, String body, Post post, UserActivity userActivity) {
        super(id);
        this.body = body;
        this.userActivity = userActivity;
        this.post = post;
    }

    @NotNull
    @Size(max = BODY_MAX_SIZE)
    @Column(nullable = false, length = BODY_MAX_SIZE)
    private String body;

    @NotNull
    @ManyToOne(optional = false, fetch = LAZY)
    @JoinColumn(nullable = false, updatable = false)
    private Post post;

    @NotNull
    @Embedded
    private UserActivity userActivity;

}

