package tn.esprit.welcamp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class User implements Serializable {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int idMembre;
    private String FirstName;
    private String LastName;
    private String Pseudo;
    private String Password;
    private String Mail;
    private String NumTel;
    private String Adresse;
    @Temporal(TemporalType.DATE)
    private Date Inscription_Date;
    private String Gender;

//-----------------------------------------------achref-----------------------------

    @OneToMany (mappedBy = "user")
    List<Claim> listC;
    @JsonIgnore

    @OneToOne
    Interrest interrest;
    @JsonIgnore

    @ManyToOne
    Role role;
//-----------------------------------------------yoyo-----------------------------

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<ToolOffer> toolOffers;
    @JsonIgnore
    @OneToMany
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<Command> orders;


//-----------------------------------------------dali-----------------------------

    @JsonIgnore
    @OneToMany
    List<CampSite>campsites;

    @JsonIgnore
    @OneToMany
    List<Activity> activities;

    @JsonIgnore
    @OneToOne
    Evaluation evaluation;

    @JsonIgnore
    @OneToMany
    List<Favorie> favories;



    @JsonIgnore
    @OneToMany
    List<Reservation> reservations;

    @JsonIgnore
    @OneToMany
    List<Reservation> reservationsConfirms;

    @JsonIgnore
    @ManyToMany(mappedBy = "userList")
    List<MatchingGroup> matchingGroups;
//-----------------------------------------------ala-----------------------------
//-----------------------------------------------nada-----------------------------

}
