package tn.esprit.welcamp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Cascade;

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
public class ToolOffer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idToolOffer;
    private int barCode;
    private String toolName;
    private String description;
    private double price;
    private String brand;
    private int weight;
    private String characteristic;
    private boolean inStock;
    @Enumerated(EnumType.ORDINAL)
    private TypeCart typeCart;
    private String RentPeriod;
    private String imageURL;
    private boolean InPromotion;
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    private  User user;
//    @ManyToMany(mappedBy = "toolOffer")
//    private List<CommandLine> commandLines;
//    @OneToMany
//    private List<CommandLine> commandLines;
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    private  CategoryTools categoryTools;
    @JsonIgnore
    @ManyToOne
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Promotion promotion;
    @OneToMany(
            cascade = {CascadeType.ALL},
            mappedBy = "toolOffer"
    )
    @JsonIgnore
    public List<ImageData> images;
}
