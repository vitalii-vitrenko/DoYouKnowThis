package com.vitrenko.doyouknowthis.domain.entity;


import lombok.*;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Embeddable
public class UserActivity {

    public UserActivity(User user, LocalDateTime createdData, LocalDateTime updatedData) {
        this.user = user;
        this.createdData = createdData;
        this.updatedData = updatedData;
    }

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(nullable = false, updatable = false)
    private User user;

    @Past
    @NotNull
    @Column(nullable = false)
    private LocalDateTime createdData;

    @Past
    private LocalDateTime updatedData;

}
