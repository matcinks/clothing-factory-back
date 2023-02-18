package pl.clothingfactory.product;

import lombok.Data;
import org.javers.core.metamodel.annotation.DiffIgnore;
import org.javers.core.metamodel.annotation.ShallowReference;
import org.javers.core.metamodel.annotation.TypeName;
import pl.clothingfactory.material.Material;
import pl.clothingfactory.product.colour.Colour;
import pl.clothingfactory.product.size.Size;

import jakarta.persistence.*;



import jakarta.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Data
@TypeName("Product") // oznaczenie typu dla Javers
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name = "template";

    @Enumerated(EnumType.STRING)
    private Category category;

    private BigDecimal price;

    @NotBlank(message = "Nowy produkt musi posiadać opis.")
    private String description;

    private String additionalInformation;

    private Double materialUsage;

    private String unitUsage;

    @ManyToMany
    @JoinTable(name = "products_materials",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "material_id"))
    @ShallowReference
    private List<Material> materials;

    @ManyToMany
    @JoinTable(name = "products_sizes",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "size_id"))
    @ShallowReference
    private List<Size> sizes;

    @ManyToMany
    @JoinTable(name = "products_colours",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "colour_id"))
    @ShallowReference
    private List<Colour> colours;

    @DiffIgnore
    @Version
    private Long version;

    @DiffIgnore
    private Date createdAt;

    @PrePersist
    public void productCreatedAt() {
        this.setCreatedAt(new Date());
    }

}