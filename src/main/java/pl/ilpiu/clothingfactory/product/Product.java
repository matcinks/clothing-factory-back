package pl.ilpiu.clothingfactory.product;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity
@Table(name = "products")
public
class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // TODO change requirement in database schema to optional
    private String name = "template";

    @NotNull
    private String description;

    private String additionalInformation;

    @Enumerated(EnumType.STRING)
    private Category category;

    @ManyToMany(targetEntity = pl.ilpiu.clothingfactory.product.Size.class)
    @JoinTable(name = "products_sizes",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "size_id"))
    @javax.validation.constraints.Size(min = 1, message = "Produkt musi nalezec do kategorii")
    private List<pl.ilpiu.clothingfactory.product.Size> sizes;

    // TODO change column name in joining table
    @ManyToMany(targetEntity = Colour.class)
    @JoinTable(name = "products_colours",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "colour_id"))
    @javax.validation.constraints.Size(min = 1, message = "Produkt musi posiadac kolor")
    private List<Colour> colours;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "price_id")
    private List<Price> prices;

    private double materialUsage;

    private String unitUsage;


}