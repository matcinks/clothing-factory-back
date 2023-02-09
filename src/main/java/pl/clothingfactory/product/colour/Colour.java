package pl.clothingfactory.product.colour;

import lombok.Data;
import org.javers.core.metamodel.annotation.DiffIgnore;
import org.javers.core.metamodel.annotation.TypeName;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Entity
@TypeName("Colour")
@Table(name = "colours")
public class Colour {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(unique = true)
    private String name;

    @DiffIgnore
    private Date createdAt;

    @PrePersist
    public void createdAt(){
        this.createdAt = new Date();
    }
}
