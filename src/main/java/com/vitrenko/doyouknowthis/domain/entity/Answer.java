package com.vitrenko.doyouknowthis.domain.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
public class Answer extends Post {

    public Answer(Long id, String body, UserActivity userActivity, Question question, int likesCount) {
        super(id, body, userActivity);
        this.question = question;
        this.likesCount = likesCount;
    }

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(nullable = false, updatable = false)
    private Question question;

    @Min(0)
    private int likesCount;

}
