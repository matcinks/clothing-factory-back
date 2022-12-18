package pl.ilpiu.clothingfactory.product.projections;

import pl.ilpiu.clothingfactory.material.projections.MaterialDetailsForComparison;
import pl.ilpiu.clothingfactory.product.Category;
import pl.ilpiu.clothingfactory.product.colour.projections.ColourDetailsForComparison;
import pl.ilpiu.clothingfactory.product.size.projections.SizeDetailsForComparison;

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
