package tn.esprit.welcamp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class CampSite implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCampsite;

    private String title ;

    private String description ;

    private String location ;

    private String natural_feature ;

    private float price ;

    private int capacity ;

    @JsonIgnore
    @OneToMany
    List<Activity> activityList;

    @JsonIgnore
    @OneToMany
    List<Evaluation> evaluations;

    @JsonIgnore
    @OneToMany
    List<Reservation> reservationList;
}
