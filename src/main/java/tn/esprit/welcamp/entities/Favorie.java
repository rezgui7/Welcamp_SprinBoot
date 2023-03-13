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
public class Favorie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int idFavorie;

    @JsonIgnore
    @OneToMany
    List<CampSite> campsites;
}
