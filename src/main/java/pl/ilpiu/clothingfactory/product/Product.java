package pl.ilpiu.clothingfactory.product;

import lombok.Data;
import pl.ilpiu.clothingfactory.material.Material;
import pl.ilpiu.clothingfactory.product.colour.Colour;
import pl.ilpiu.clothingfactory.product.size.Size;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "products")
public class Product extends ProductBasicDetailsDTO {

    // TODO blokada na bazie danych, żeby nie było możliwości zmiany jednego obiektu przez kilka osób jednocześnie
    // optimistic / pesimistic lock na bazie danych -> baeldung

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;


//    private String name = "template";

//    @NotNull
//    private String description;

    private String additionalInformation;

//    @Enumerated(EnumType.STRING)
//    private Category category;

//    private BigDecimal price;

    private double materialUsage;

    private String unitUsage;

    @ManyToMany(targetEntity = Material.class, cascade = {CascadeType.PERSIST})
    @JoinTable(name = "products_materials",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "material_id"))
    @javax.validation.constraints.Size(min = 1, message = "Produkt musi być wykononany z conajmniej jednego materiału")
    private List<Material> materials;

    @ManyToMany(targetEntity = Size.class, cascade = {CascadeType.PERSIST})
    @JoinTable(name = "products_sizes",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "size_id"))
    @javax.validation.constraints.Size(min = 1, message = "Produkt musi mieć rozmiar")
    private List<Size> sizes;

    @ManyToMany(targetEntity = Colour.class, cascade = {CascadeType.PERSIST})
    @JoinTable(name = "products_colours",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "colour_id"))
    @javax.validation.constraints.Size(min = 1, message = "Produkt musi posiadac kolor")
    private List<Colour> colours;

    private Date createdAt;

    // TODO przy testach ustawić zegar jako argument Clock -> google
    @PrePersist
    public void productCreatedAt(){
        this.setCreatedAt(new Date());
    }

}