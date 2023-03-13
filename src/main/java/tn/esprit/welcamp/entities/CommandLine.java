package tn.esprit.welcamp.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class CommandLine implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idCart;
    private boolean empty;
    private double totalPrice;
    private int toolsQuantity;
    private Date createdDate;


    //    @ManyToMany
//    private List<ToolOffer> toolOffer;
    @ManyToOne
    @JoinColumn(name = "idToolOffer")
    private ToolOffer toolOffer;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


}
