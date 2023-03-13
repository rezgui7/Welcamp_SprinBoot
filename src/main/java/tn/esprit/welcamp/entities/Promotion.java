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
public class Promotion implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idPromotion;
    private String promotionCode;
    private double amount;
    private Date expires;

    @JsonIgnore
    @OneToMany(mappedBy = "promotion")
    private List<CategoryTools> categoryTools;

}
