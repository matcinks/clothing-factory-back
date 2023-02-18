package pl.clothingfactory.common;


import jakarta.persistence.*;
import lombok.Data;
import pl.clothingfactory.product.colour.Colour;
import pl.clothingfactory.product.Product;
import pl.clothingfactory.product.size.Size;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import java.util.Date;

@MappedSuperclass
@Data
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id", name = "product_id")
    private Product product;

    @ManyToOne(targetEntity = Size.class)
    @JoinColumn(referencedColumnName = "id", name = "size_id")
    private Size size;

    @ManyToOne(targetEntity = Colour.class)
    @JoinColumn(name = "colour_id", referencedColumnName = "id")
    private Colour colour;

    private Integer amount;

    @Min(1)
    @Max(50)
    private Integer priority;

    @Enumerated(EnumType.STRING)
    private Status status = Status.ZAPLANOWANE;

    private Date scheduledOn;
}
