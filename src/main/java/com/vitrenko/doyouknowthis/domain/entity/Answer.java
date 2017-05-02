package com.vitrenko.doyouknowthis.domain.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import static javax.persistence.FetchType.LAZY;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
public class Answer extends Post {

    public Answer(Long id, String body, Question question, int likesCount, UserActivity userActivity) {
        super(id, body, userActivity);
        this.question = question;
        this.likesCount = likesCount;
    }

    @NotNull
    @ManyToOne(optional = false, fetch = LAZY)
    @JoinColumn(nullable = false, updatable = false)
    private Question question;

    @Min(0)
    @Column(nullable = false)
    private int likesCount = 0;

}
