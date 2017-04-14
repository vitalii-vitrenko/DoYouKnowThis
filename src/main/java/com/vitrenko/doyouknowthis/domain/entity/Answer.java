package com.vitrenko.doyouknowthis.domain.entity;

import lombok.*;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
public class Answer extends Post {

    public Answer(Long id, String body, User user, List<Comment> comments, Question question, int likeCount) {
        super(id, body, user, comments);
        this.question = question;
        this.likeCount = likeCount;
    }

    @ManyToOne(optional = false)
    @NotNull
    private Question question;

    @Min(0)
    private int likeCount;
}
