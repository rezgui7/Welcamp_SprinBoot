package tn.esprit.welcamp.entities;


import lombok.*;

import javax.persistence.*;
import java.time.LocalTime;



@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idActivity;

    private String name ;


    private LocalTime StartHour ;


    private LocalTime FinishHour ;

    private String actDescription;

    private int participentNumber;


}
