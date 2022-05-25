package pl.ilpiu.clothingfactory.product;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Table(name = "prices")
public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "price_id")
    private Long priceId;

    @ManyToOne(targetEntity = Product.class)
    private Product product;

    @NotNull
    private BigDecimal price;

    private Date validFrom;

    @PrePersist
    void validFrom() {
        this.validFrom = new Date();
    }

    // TODO add customer company (external, internal)
}
