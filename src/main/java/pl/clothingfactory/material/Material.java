package pl.clothingfactory.material;

import lombok.Data;
import org.javers.core.metamodel.annotation.DiffIgnore;
import org.javers.core.metamodel.annotation.TypeName;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@Entity
@TypeName("Colour")
@Table(name = "materials")
public class Material {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Materiał musi mieć nazwę.")
    private String name;

    private String additionalDescription;

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(name = "materials_compositions")
    @Size(min = 1, message = "Materiał musi się składać z minimum jednego surowca.")
    private List<Composition> composition;

    @NotNull
    private BigDecimal price;

    private String priceUnit;
    @DiffIgnore
    private Date createdAt;

    @PrePersist
    void createdAt() {
        this.createdAt = new Date();
    }


}
