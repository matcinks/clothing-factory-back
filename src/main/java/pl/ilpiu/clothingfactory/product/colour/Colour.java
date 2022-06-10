package pl.ilpiu.clothingfactory.product.colour;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Entity
@Table(name = "colours")
public class Colour {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(unique = true)
    private String name;

    private Date createdAt;

    @PrePersist
    public void createdAt(){
        this.createdAt = new Date();
    }
}
