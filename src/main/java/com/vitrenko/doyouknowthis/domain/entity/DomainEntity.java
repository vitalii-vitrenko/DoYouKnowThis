package com.vitrenko.doyouknowthis.domain.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@MappedSuperclass
public abstract class DomainEntity {

    @Id
    @GeneratedValue
    private Long id;
}
