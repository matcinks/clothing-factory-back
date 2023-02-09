package pl.clothingfactory.product.archive;

import org.apache.commons.lang3.StringUtils;
import org.javers.core.Javers;
import org.javers.core.diff.Change;
import org.javers.core.diff.changetype.ValueChange;
import org.javers.core.diff.changetype.container.ListChange;
import org.springframework.stereotype.Service;

import pl.clothingfactory.material.Material;
import pl.clothingfactory.product.Product;
import pl.clothingfactory.product.colour.Colour;
import pl.clothingfactory.product.size.Size;

import java.util.List;

import static java.util.Optional.ofNullable;


@Service
public class ProductChangesService {
    private final ProductChangesRepository productChangesRepository;
    private final Javers javers;

    private static final String VALUE_CHANGE = "ValueChange";
    private static final String LIST_CHANGE = "ListChange";
    private static final String MATERIALS = "materials";
    private static final String SIZES = "sizes";
    private static final String COLOURS = "colours";
    private static final String NAME = "name";
    private static final String PRICE = "price";
    private static final String DESCRIPTION = "description";
    private static final String ADDITIONAL_INFORMATION = "additionalInformation";
    private static final String MATERIAL_USAGE = "materialUsage";
    private static final String UNIT_USAGE = "unitUsage";
    private static final String CATEGORY = "category";


    ProductChangesService(final ProductChangesRepository productChangesRepository, final Javers javers) {
        this.productChangesRepository = productChangesRepository;
        this.javers = javers;
    }

    List<ProductChanges> getAllProductChangesByProductId(Long productId) {
        return productChangesRepository.findAllByProductId(productId);
    }

    public void archiveProductChanges(Product beforeUpdate, Product afterUpdate) {

        if (!beforeUpdate.getId().equals(afterUpdate.getId())) throw new IllegalArgumentException("Product id must be the same");

        List<ProductChanges> changesToCommit = javers.compare(beforeUpdate, afterUpdate).getChanges().stream()
                .map(change -> processProductChange(change, beforeUpdate, afterUpdate))
                .toList();
        productChangesRepository.saveAll(changesToCommit);

    }

    private ProductChanges processProductChange(Change change, Product beforeUpdate, Product afterUpdate){
        String changeType = change.getClass().getSimpleName();
        Long productId = beforeUpdate.getId();

        if (VALUE_CHANGE.equals(changeType)) {
            return processValueChange(change, productId);
        }
        else if (LIST_CHANGE.equals(changeType)) {
            return processListChange(change, beforeUpdate, afterUpdate, productId);
        }
        else {
            throw new UnsupportedOperationException("Unsupported changeType: " + changeType);
        }
    }

    private ProductChanges processValueChange(Change change, Long productId){
        ValueChange valueChange = (ValueChange) change;
        return createProductChange(productId,
                valueChange.getPropertyName(),
                ofNullable(valueChange.getLeft()).map(Object::toString).orElse(StringUtils.EMPTY),
                ofNullable(valueChange.getRight()).map(Object::toString).orElse(StringUtils.EMPTY)
        );
    }

    private ProductChanges processListChange(Change change,Product beforeUpdate, Product afterUpdate, Long productId) {
        String propertyName = ((ListChange) change).getPropertyName();
        ProductChanges productChanges = null;
        switch (propertyName){
            case MATERIALS -> productChanges = createProductChange(
                    productId,
                    propertyName,
                    beforeUpdate.getMaterials().stream().map(Material::getId).toList().toString(),
                    afterUpdate.getMaterials().stream().map(Material::getId).toList().toString()
            );
            case SIZES -> productChanges = createProductChange(
                    productId,
                    propertyName,
                    beforeUpdate.getSizes().stream().map(Size::getId).toList().toString(),
                    afterUpdate.getSizes().stream().map(Size::getId).toList().toString()

            );
            case COLOURS -> productChanges = createProductChange(
                    productId,
                    propertyName,
                    beforeUpdate.getColours().stream().map(Colour::getId).toList().toString(),
                    afterUpdate.getColours().stream().map(Colour::getId).toList().toString()
            );
        }
        return productChanges;
    }

    private ProductChanges createProductChange(final Long productId, final String propertyName, final String beforeUpdate, final String afterUpdate) {
        return new ProductChanges(
                productId,
//                propertyName,
                setTranslatedPropertyName(propertyName),
                beforeUpdate,
                afterUpdate,
                "test" //TODO change to user logged into system
        );
    }

    private String setTranslatedPropertyName(String propertyName) {
        String translatedPropertyName = null;
        switch (propertyName) {
            case NAME -> translatedPropertyName =  "Nazwa";
            case DESCRIPTION -> translatedPropertyName = "Opis";
            case PRICE -> translatedPropertyName = "Cena";
            case MATERIALS -> translatedPropertyName = "Lista materiałów";
            case SIZES -> translatedPropertyName = "Lista rozmiarów";
            case COLOURS -> translatedPropertyName = "Lista kolorów";
            case ADDITIONAL_INFORMATION -> translatedPropertyName = "Informacje szczegółowe";
            case MATERIAL_USAGE -> translatedPropertyName = "Zużycie materiału";
            case UNIT_USAGE -> translatedPropertyName = "Jednotska zużycia";
            case CATEGORY -> translatedPropertyName = "Kategoria";
            default -> translatedPropertyName = propertyName;
        }
        return translatedPropertyName;
    }

}