package pl.ilpiu.clothingfactory.product;

import lombok.Data;
import org.javers.core.metamodel.annotation.DiffIgnore;
import org.javers.core.metamodel.annotation.ShallowReference;
import org.javers.core.metamodel.annotation.TypeName;
import pl.ilpiu.clothingfactory.material.Material;
import pl.ilpiu.clothingfactory.product.colour.Colour;
import pl.ilpiu.clothingfactory.product.size.Size;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
    protected Long id;

    // TODO change requirement in database schema to optional
    private String name = "template";

    @Enumerated(EnumType.STRING)
    private Category category;

    private BigDecimal price;

    @NotNull
    private String description;

    private String additionalInformation;

    private Double materialUsage;

    private String unitUsage;

    //    @ManyToMany(targetEntity = Material.class, cascade = {CascadeType.PERSIST})
    @ManyToMany
    @JoinTable(name = "products_materials",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "material_id"))
    @javax.validation.constraints.Size(min = 1, message = "Produkt musi być wykononany z conajmniej jednego materiału")
    @ShallowReference
    private List<Material> materials;

    //    @ManyToMany(targetEntity = Size.class, cascade = {CascadeType.PERSIST})
    @ManyToMany
    @JoinTable(name = "products_sizes",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "size_id"))
    @javax.validation.constraints.Size(min = 1, message = "Produkt musi mieć rozmiar")
    @ShallowReference
    private List<Size> sizes;

    //    @ManyToMany(targetEntity = Colour.class, cascade = {CascadeType.PERSIST})
    @ManyToMany
    @JoinTable(name = "products_colours",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "colour_id"))
    @javax.validation.constraints.Size(min = 1, message = "Produkt musi posiadac kolor")
    @ShallowReference
    private List<Colour> colours;

    // adnotacja do zalozenia locka na bazie danych -> zabezpieczenie przed edycja jednego obiektu przez kilka osób jednocześnie
    @DiffIgnore
    @Version
    private Long version;

    // Adnotacja @DiffIgnore ignoruje pole przy porównywaniu obiektów przez Javers
    @DiffIgnore
    private Date createdAt;

    // TODO przy testach ustawić zegar jako argument Clock -> google
    @PrePersist
    public void productCreatedAt() {
        this.setCreatedAt(new Date());
    }

}