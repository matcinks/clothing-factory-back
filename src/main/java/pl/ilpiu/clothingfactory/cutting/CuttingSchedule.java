package pl.ilpiu.clothingfactory.cutting;

import jdk.jshell.Snippet;
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
@Table(name = "cutting_schedules")
class CuttingSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Product product;

    @ManyToOne
    private Colour colour;

    @ManyToOne
    private Size size;

    @NotNull
    private Integer amount;

    @Enumerated(EnumType.STRING)
    private Status status = Status.ZAPLANOWANE;

    // TODO Plan schedule priorities, maybe add some restrictions, check restrictions @Min, @Max
    @Min(1)
    @Max(10)
    private Integer priority;
    private Date scheduledOn;

    private void scheduledOn(){
        this.scheduledOn = new Date();
    }
}