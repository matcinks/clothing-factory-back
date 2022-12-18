package pl.ilpiu.clothingfactory.product;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.ilpiu.clothingfactory.exception.ObjectNotFoundInDBException;
import pl.ilpiu.clothingfactory.material.Material;
import pl.ilpiu.clothingfactory.material.MaterialService;
import pl.ilpiu.clothingfactory.product.archive.ProductChangesService;
import pl.ilpiu.clothingfactory.product.colour.Colour;
import pl.ilpiu.clothingfactory.product.colour.ColourService;
import pl.ilpiu.clothingfactory.product.projections.ProductDetailsBasicInfo;
import pl.ilpiu.clothingfactory.product.size.Size;
import pl.ilpiu.clothingfactory.product.size.SizeService;

import java.util.List;

@Service
@RequiredArgsConstructor
// TODO zmienic public na private
public class ProductService {

    private final ProductRepository productRepository;
    private final MaterialService materialService;
    private final ColourService colourService;
    private final SizeService sizeService;

    private final ProductChangesService productChangesService;

    // TODO sprawdzic roznice .toList() vs .collect(Collectors.toList())


    List<ProductDetailsBasicInfo> getAllProducts() {
        return productRepository.findAllBy(ProductDetailsBasicInfo.class);
    }

    // TODO zmienic na zwrotke z ProductDetailsDTO
    List<Product> getAllProducts(Pageable page) {
        return productRepository
                .findAll(page)
                .getContent();
    }

    Product createProduct(Product newProduct) {
        setValuesOfColourMaterialSize(newProduct);
        return productRepository
                .save(newProduct);
    }

    private void setValuesOfColourMaterialSize(Product newProduct) {
        newProduct.setColours(colourService.getColoursById(newProduct.getColours().stream().map(Colour::getId).toList()));
        newProduct.setSizes(sizeService.getSizesById(newProduct.getSizes().stream().map(Size::getId).toList()));
        newProduct.setMaterials(materialService.getMaterialsById(newProduct.getMaterials().stream().map(Material::getId).toList()));
    }

    Product getProductById(Long id) {
        return productRepository
                .findById(id)
                .orElseThrow(() -> new ObjectNotFoundInDBException("Nie znaleziono w bazie produktu o numerze id: " + id + "."));
    }

    // TODO co zwracać w tej metodzie? void czy Product? + czy zrobić sprawdzenie updatedInfo.id == productInDBId?
    void updateProduct(Product updatedInfo) {

//        // id aktualizowanego produktu
//        Long productId = updatedInfo.getId();
//
//        // produkt z bazy danych do porównania ze zmianami
//        Product productFromDB = (Product)productRepository.findProductDetailsForComparisonById(productId)
//                .orElseThrow(() -> new ObjectNotFoundInDBException("Nie znaleziono w bazie produktu o numerze id: " + productId + "."));

        // archiwizacja zmian produktu
        productChangesService.archiveProductChanges(getProductById(updatedInfo.getId()), updatedInfo);

        // zapisanie zmian w bazie
        productRepository.save(updatedInfo);
    }
}