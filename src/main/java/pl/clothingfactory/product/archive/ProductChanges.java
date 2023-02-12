package pl.clothingfactory.product.archive;

import lombok.AllArgsConstructor;
import lombok.Data;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
    // TODO zmienić usera
//    private String user;
//    private User userId;

    public ProductChanges() {
    }

    ProductChanges(Long productId, String propertyName, String valueBefore, String valueAfter, String user) {
        this.productId = productId;
        this.propertyName = propertyName;
        this.valueBefore = valueBefore;
        this.valueAfter = valueAfter;
//        this.user = user;
    }

    @PrePersist
    public void changeCreatedAt() {
        this.changeDate = new Date();
    }
}
