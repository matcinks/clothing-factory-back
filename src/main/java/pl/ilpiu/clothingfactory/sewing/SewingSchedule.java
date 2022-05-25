package pl.ilpiu.clothingfactory.sewing;

import lombok.Data;
import pl.ilpiu.clothingfactory.common.Status;
import pl.ilpiu.clothingfactory.product.Colour;
import pl.ilpiu.clothingfactory.product.Product;
import pl.ilpiu.clothingfactory.product.Size;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Entity
@Table(name = "sewing_schedules")
class SewingSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Product product;

    @ManyToOne
    private Size size;

    @ManyToOne
    private Colour colour;

    @ManyToOne
    private Seamstress seamstress;

    @NotNull
    private Integer amount;

    @Enumerated(EnumType.STRING)
    private Status status = Status.ZAPLANOWANE;

    // TODO Plan schedule priorities, maybe add some restrictions, check restrictions @Min, @Max
    @Min(1)
    @Max(10)
    private Integer priority;
    private Date scheduledOn;

    @PrePersist
    private void scheduledOn(){
        this.scheduledOn = new Date();
    }
}
