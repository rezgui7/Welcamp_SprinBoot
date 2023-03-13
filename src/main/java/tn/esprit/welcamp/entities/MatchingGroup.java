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
public class MatchingGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int idGroupe;

    private String groupName ;

    private Date creationDate;

    private int campersNumber ;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    List<User> userList;
}
