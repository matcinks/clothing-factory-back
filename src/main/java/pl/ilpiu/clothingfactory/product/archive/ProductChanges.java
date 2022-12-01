package pl.ilpiu.clothingfactory.product.archive;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Entity(name = "products_changes")
class ProductChanges {

    //TODO dopisać walidacje pól

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long changeId;

    @NotNull
    private Long productId;

    @NotNull
    private String propertyName;

    @NotNull
    private String valueBefore;

    @NotNull
    private String valueAfter;
    private Date changeDate;
    // TODO przypisac dane usera ktory dokonal zmiany
//    private User userId;

    @PrePersist
    public void changeCreatedAt() {
        this.changeDate = new Date();
    }
}
