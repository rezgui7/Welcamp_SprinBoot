package tn.esprit.welcamp.entities;

import lombok.*;

import javax.persistence.Entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Interrest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int idInterrest ;

    private String naturalFeatures;

    @Temporal(TemporalType.DATE)
    private Date date;

    private String activityName ;

    private int age ;

    @JsonIgnore
    @OneToOne(mappedBy = "interrest")
    User user;
}
