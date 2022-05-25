package pl.ilpiu.clothingfactory.knitting;

import lombok.Data;
import pl.ilpiu.clothingfactory.common.Status;
import pl.ilpiu.clothingfactory.product.Colour;
import pl.ilpiu.clothingfactory.knitting.KnittingDevice;
import pl.ilpiu.clothingfactory.product.Product;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@Entity
@Table(name = "knitting_schedules")
class KnittingSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Product product;

    @ManyToOne
    private KnittingDevice knittingDevice;

    @ManyToOne
    private Colour colour;

    @NotNull(message = "Knitting schedule entry should have positive amount.")
    private Integer amount;

    // TODO check default status
    @Enumerated(EnumType.STRING)
    private Status status = Status.ZAPLANOWANE;

    // TODO Plan schedule priorities, maybe add some restrictions, check restrictions @Min, @Max
    @Min(1)
    @Max(10)
    private Integer priority;
    private Date scheduledOn;

    @PrePersist
    public void scheduledOn(){
        this.scheduledOn = new Date();
    }


}
