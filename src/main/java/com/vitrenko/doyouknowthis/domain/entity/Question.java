package com.vitrenko.doyouknowthis.domain.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

import static javax.persistence.CascadeType.REMOVE;

@Data
@NoArgsConstructor
@Entity
@EqualsAndHashCode(callSuper = true, exclude = "answers")
@ToString(callSuper = true)
public class Question extends Post {

    public Question(Long id, String header, String body, User user, List<Comment> comments, List<Answer> answers) {
        super(id, body, user, comments);
        this.header = header;
        this.answers = answers;
    }

    @NotNull
    @Size(max = 50)
    private String header;

    @OneToMany(mappedBy = "question", cascade = REMOVE, orphanRemoval = true)
    private List<Answer> answers;

}
