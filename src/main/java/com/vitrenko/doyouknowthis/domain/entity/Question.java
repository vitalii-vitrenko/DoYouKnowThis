package com.vitrenko.doyouknowthis.domain.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.REMOVE;

@Data
@NoArgsConstructor
@Entity
@EqualsAndHashCode(callSuper = true, exclude = "answers")
@ToString(callSuper = true)
public class Question extends Post {

    public static final int HEADER_MAX_LENGTH = 50;

    public Question(Long id, String body, String header, int price, UserActivity userActivity) {
        super(id, body, userActivity);
        this.header = header;
        this.price = price;
    }

    @NotNull
    @Size(max = HEADER_MAX_LENGTH)
    @Column(length = HEADER_MAX_LENGTH)
    private String header;

    @Min(1)
    @Column(nullable = false)
    private int price;

    @OneToOne
    @JoinColumn
    private Answer selectedAnswer;

    @OneToMany(mappedBy = "question", cascade = REMOVE, orphanRemoval = true)
    private List<Answer> answers = new ArrayList<>();

}
