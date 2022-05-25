package pl.ilpiu.clothingfactory.labels;

import lombok.Data;
import pl.ilpiu.clothingfactory.common.Status;
import pl.ilpiu.clothingfactory.product.Product;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Entity
@Table(name = "labels_schedules")
class LabelsSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Product product;

//    TODO consider minimal value of washing labels, if added add information to sql creation schema file
//    @Min(value = 1, message = "Nalezy zaplanowac przynajmniej jedna wszywke.")
    private Integer washingLabelsAmount;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Status washingLabelsStatus = Status.ZAPLANOWANE;

//    TODO consider minimal value of price labels, if added add information to sql creation schema file
//    @Min(value = 1, message = "Nalezy zaplanowac przynajmniej jedna etykiete")
    private Integer priceLabelsAmount;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Status priceLabelsStatus;

    private Date scheduledOn;

    @PrePersist
    private void scheduledOn(){
        this.scheduledOn = new Date();
    }
}
