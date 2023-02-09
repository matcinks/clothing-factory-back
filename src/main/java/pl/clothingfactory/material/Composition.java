package pl.clothingfactory.material;

import lombok.Data;
import org.javers.core.metamodel.annotation.TypeName;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@TypeName("Composition")
@Table(name = "compositions")
public class Composition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @NotNull
    private RawMaterial rawMaterial;

    @NotNull
    private Integer percentage;

}
