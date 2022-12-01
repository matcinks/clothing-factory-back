package pl.ilpiu.clothingfactory.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@MappedSuperclass
@Data
@NoArgsConstructor
class ProductBasicDetailsDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    // TODO change requirement in database schema to optional
    private String name = "template";

    @Enumerated(EnumType.STRING)
    private Category category;

//    private BigDecimal price;

    @NotNull
    private String description;

    public ProductBasicDetailsDTO(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.category = product.getCategory();
//        this.price = product.getPrice();
        this.description = product.getDescription();
    }
}

