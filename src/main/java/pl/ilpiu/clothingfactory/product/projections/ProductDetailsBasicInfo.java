package pl.ilpiu.clothingfactory.product.projections;

import pl.ilpiu.clothingfactory.product.Category;
import java.math.BigDecimal;

public interface ProductDetailsBasicInfo {
    Long getId();
    String getName();
    Category getCategory();
    BigDecimal getPrice();
    String getDescription();
}
