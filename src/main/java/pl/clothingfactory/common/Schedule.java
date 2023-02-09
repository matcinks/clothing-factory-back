package pl.clothingfactory.common;


import lombok.Data;
import pl.clothingfactory.product.colour.Colour;
import pl.clothingfactory.product.Product;
import pl.clothingfactory.product.size.Size;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
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

    //    @NotNull
    private Integer amount;

    // TODO Plan schedule priorities, maybe add some restrictions, check restrictions @Min, @Max
    @Min(1)
    @Max(50)
//    @NotNull(message = "Schedule entry should have positive amount.")
    private Integer priority;

    // TODO check default status
    // TODO add more statuses
    @Enumerated(EnumType.STRING)
    private Status status = Status.ZAPLANOWANE;

    private Date scheduledOn;

//    @PrePersist
//    private void scheduledOn() {
//        this.scheduledOn = new Date();
//    }
}
