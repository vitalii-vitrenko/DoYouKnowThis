package com.vitrenko.doyouknowthis.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@ToString(callSuper = true)
@Entity
public class Comment extends DomainEntity {


    @NotNull
    private String body;

    @ManyToOne(optional = false)
    @NotNull
    private Post post;
}

