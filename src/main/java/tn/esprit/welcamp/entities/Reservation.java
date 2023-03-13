package tn.esprit.welcamp.entities;

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
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int idReservation;

    @Temporal(TemporalType.DATE)
    private Date startSate;

    @Temporal(TemporalType.DATE)
    private Date finishDate;

    private int AdultNumber ;

    private int childNumber ;

    private int petsNumber ;

    private float totalPrice ;


}
