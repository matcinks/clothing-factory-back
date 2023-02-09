package pl.clothingfactory.product.projections;

import pl.clothingfactory.product.Category;

import java.math.BigDecimal;

public interface ProductDetailsBasicInfo {
    Long getId();
    String getName();
    Category getCategory();
    BigDecimal getPrice();
    String getDescription();
}
