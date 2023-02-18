package pl.clothingfactory.product;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.clothingfactory.exception.ObjectNotFoundInDBException;
import pl.clothingfactory.exception.ObjectVersionInconsistentException;
import pl.clothingfactory.material.Material;
import pl.clothingfactory.material.MaterialService;
import pl.clothingfactory.product.archive.ProductChangesService;
import pl.clothingfactory.product.colour.Colour;
import pl.clothingfactory.product.colour.ColourService;
import pl.clothingfactory.product.projections.ProductDetailsBasicInfo;
import pl.clothingfactory.product.projections.ProductMaterials;
import pl.clothingfactory.product.size.Size;
import pl.clothingfactory.product.size.SizeService;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final MaterialService materialService;
    private final ColourService colourService;
    private final SizeService sizeService;

    private final ProductChangesService productChangesService;

    List<ProductDetailsBasicInfo> getAllProducts(List<Category> checkedCategories, String search) {
        if (checkedCategories != null && !checkedCategories.isEmpty()) {
            if (search != null && !search.isEmpty()) {
                if (checkForId(search)) return productRepository.findAllByAndCategoryInAndId(ProductDetailsBasicInfo.class, checkedCategories, Long.parseLong(search));
                return productRepository.findAllByAndCategoryInAndNameContainingIgnoreCase(ProductDetailsBasicInfo.class, checkedCategories, search);
            }
            return productRepository.findAllByAndCategoryIn(ProductDetailsBasicInfo.class, checkedCategories);
        }
        if (search != null && !search.isEmpty()) {
            if (checkForId(search)) return productRepository.findAllByAndId(ProductDetailsBasicInfo.class, Long.parseLong(search));
            return productRepository.findAllByAndNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(ProductDetailsBasicInfo.class, search, search);
        }
        return productRepository.findAllBy(ProductDetailsBasicInfo.class);
    }

    private boolean checkForId (String search) {
        try {
            Long id = Long.parseLong(search);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    Product createProduct(Product newProduct) {
        if (!newProduct.getMaterials().isEmpty()) setValuesOfMaterials(newProduct);
        if (!newProduct.getColours().isEmpty()) setValuesOfColours(newProduct);
        if (!newProduct.getSizes().isEmpty()) setValuesOfSizes(newProduct);

        return productRepository
                .save(newProduct);
    }

    private void setValuesOfColours(Product newProduct) {
        newProduct.setColours(colourService.getColoursById(newProduct.getColours().stream().map(Colour::getId).toList()));
    }
    private void setValuesOfSizes(Product newProduct) {newProduct.setSizes(sizeService.getSizesById(newProduct.getSizes().stream().map(Size::getId).toList()));;
    }
    private void setValuesOfMaterials(Product newProduct) {
        newProduct.setMaterials(materialService.getMaterialsById(newProduct.getMaterials().stream().map(Material::getId).toList()));
    }

     public Product getProductById(Long id) {
        return productRepository
                .findById(id)
                .orElseThrow(() -> new ObjectNotFoundInDBException("Nie znaleziono w bazie produktu o numerze id: " + id + "."));
    }

    void updateProduct(Product updatedInfo) {
        Product productInDB = getProductById(updatedInfo.getId());

        if (updatedInfo.getVersion() != productInDB.getVersion()) {
            throw new ObjectVersionInconsistentException("Produkt został zaaktualizowany przez innego użytkownika. Odśwież stronę i spróbuj ponownie.");
        }

        productChangesService.archiveProductChanges(getProductById(updatedInfo.getId()), updatedInfo);

        productRepository.save(updatedInfo);
    }

    List<Category> getAllCategories() {
        return Arrays.asList(Category.values());
    }

    ProductMaterials getProductMaterials(final Long id) {
        if (!productRepository.existsById(id)) {
            throw new ObjectNotFoundInDBException("Nie znaleziono w bazie produktu o numerze id: " + id + ".");
        }
        return productRepository.findAllById(id);
    }
}