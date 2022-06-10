package pl.ilpiu.clothingfactory.material;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "materials")
public class Material {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    private String additionalDescription;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "materials_compositions")
    @Size(min = 1, message = "Produkt musi sie skladac z minimum jednego skladnika.")
    private List<Composition> composition;

    @NotNull
    private BigDecimal price;

    // TODO This field is supposed to store unit type. It is String type, check it's behaviour and usefulness later
    private String priceUnit;

    private Date createdAt;

    @PrePersist
    void createdAt() {
        this.createdAt = new Date();
    }

}
