package pl.ilpiu.clothingfactory.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import pl.ilpiu.clothingfactory.material.Material;
import pl.ilpiu.clothingfactory.product.colour.Colour;
import pl.ilpiu.clothingfactory.product.price.Price;
import pl.ilpiu.clothingfactory.product.size.Size;

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

    // TODO tu powinien byc raczej material id
    // lub dodac kolejna opcje w serwisie z wyszukiwaniem
//    @ManyToOne(targetEntity = Material.class)
    @ManyToOne
    private Material material;

    // TODO check if targetEntity is still nessesery
    @ManyToMany(targetEntity = Size.class, cascade = CascadeType.ALL)
    @JoinTable(name = "products_sizes",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "size_id"))
    @javax.validation.constraints.Size(min = 1, message = "Produkt musi mieć rozmiar")
    private List<Size> sizes;

    // TODO change column name in joining table
    // TODO check if targetEntity is still nessesery
    @ManyToMany(targetEntity = Colour.class, cascade = CascadeType.ALL)
    @JoinTable(name = "products_colours",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "colour_id"))
    @javax.validation.constraints.Size(min = 1, message = "Produkt musi posiadac kolor")
    private List<Colour> colours;

    // TODO check if fetch is still nessesery, after adding cascade type
//    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JsonManagedReference
    @JsonIgnore
    @OneToMany(mappedBy = "product")
    private List<Price> prices;

    private double materialUsage;

    private String unitUsage;


}