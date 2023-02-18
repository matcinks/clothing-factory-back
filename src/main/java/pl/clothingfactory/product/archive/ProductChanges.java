package pl.clothingfactory.product.archive;

import lombok.AllArgsConstructor;
import lombok.Data;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.util.Date;

@Data
@Entity(name = "products_changes")
class ProductChanges {

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
