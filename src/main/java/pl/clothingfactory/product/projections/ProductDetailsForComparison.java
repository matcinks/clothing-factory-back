package pl.clothingfactory.product.projections;

import pl.clothingfactory.material.projections.MaterialDetailsForComparison;
import pl.clothingfactory.product.Category;
import pl.clothingfactory.product.size.projections.SizeDetailsForComparison;
import pl.clothingfactory.product.colour.projections.ColourDetailsForComparison;

import java.math.BigDecimal;
import java.util.List;

public interface ProductDetailsForComparison {
    Long getId();
    String getName();
    Category getCategory();
    BigDecimal getPrice();
    String getDescription();
    String getAdditionalInformation();
    Double getMaterialUsage();
    String getUnitUsage();
    List<MaterialDetailsForComparison> getMaterials();
    List<SizeDetailsForComparison> getSizes();
    List<ColourDetailsForComparison> getColours();
}
