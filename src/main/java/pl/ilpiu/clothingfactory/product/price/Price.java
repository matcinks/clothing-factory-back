package pl.ilpiu.clothingfactory.product.price;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import pl.ilpiu.clothingfactory.product.Product;

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

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
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
