package com.vitrenko.doyouknowthis.domain.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

import static javax.persistence.CascadeType.REMOVE;

@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(exclude = {"comments"}, callSuper = false)
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Post extends DomainEntity{

    public Post(Long id, String body, User user, List<Comment> comments) {
        super(id);
        this.body = body;
        this.comments = comments;
        this.user = user;
    }

    @NotNull
    @Size(max = 1000)
    private String body;


    @ManyToOne(optional = false)
    private User user;

    @OneToMany(mappedBy = "post", cascade = REMOVE, orphanRemoval = true)
    private List<Comment> comments;

}
