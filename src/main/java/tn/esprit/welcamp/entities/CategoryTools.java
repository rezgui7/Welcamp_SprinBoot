package tn.esprit.welcamp.entities;

import lombok.*;

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
public class CategoryTools implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idCategoryTools;
    private String category;

    @OneToMany(mappedBy = "categoryTools")
    private List<ToolOffer> toolOffers;
    @ManyToOne(cascade = CascadeType.ALL)
    private  Promotion promotion;

}
