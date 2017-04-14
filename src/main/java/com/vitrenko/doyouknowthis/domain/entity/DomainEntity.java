package com.vitrenko.doyouknowthis.domain.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public abstract class DomainEntity implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
}
