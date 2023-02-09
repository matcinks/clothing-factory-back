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

    // TODO moze przerobic na switch - case
    // switch argumenty
    // case 1 - z 1 argumentem
    // case 2 - z 2 argumentem
    // case 3 - z oboma argumentami
    // default - bez
    // TODO przerobić na interfejs i jego implementacje w zależności od argumentu
    // TODO zastanowic sie nad zasadnoscia wyszukiwania po id -> jest przeciez na to metoda
    List<ProductDetailsBasicInfo> getAllProducts(List<Category> checkedCategories, String search) {
        // sprawdzenie, czy przekazane argumenty istnieją i czy nie są nullami
        if (checkedCategories != null && !checkedCategories.isEmpty()) {
            if (search != null && !search.isEmpty()) {
                if (checkForId(search)) return productRepository.findAllByAndCategoryInAndId(ProductDetailsBasicInfo.class, checkedCategories, Long.parseLong(search));
                return productRepository.findAllByAndCategoryInAndNameContainingIgnoreCase(ProductDetailsBasicInfo.class, checkedCategories, search);
            }
            return productRepository.findAllByAndCategoryIn(ProductDetailsBasicInfo.class, checkedCategories);
        }
        if (search != null && !search.isEmpty()) {
            if (checkForId(search)) return productRepository.findAllByAndId(ProductDetailsBasicInfo.class, Long.parseLong(search));
//            return productRepository.findAllByAndNameContainingIgnoreCase(ProductDetailsBasicInfo.class, search);
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

    // TODO zmienic na zwrotke z ProductDetailsDTO (projekcje)
    List<Product> getAllProducts(Pageable page) {
        return productRepository
                .findAll(page)
                .getContent();
    }

    Product createProduct(Product newProduct) {
        // metoda wykorzystywana poprzednio, kiedy pola Sizes, Colours i Materials były wymagane
        // setValuesOfColourMaterialSize(newProduct);

        if (!newProduct.getMaterials().isEmpty()) setValuesOfMaterials(newProduct);
        if (!newProduct.getColours().isEmpty()) setValuesOfColours(newProduct);
        if (!newProduct.getSizes().isEmpty()) setValuesOfSizes(newProduct);

        return productRepository
                .save(newProduct);
    }

    // metody do ustawienia koloru, rozmiaru i materialu
    // TODO przerobic na 1 metode generyczna
    private void setValuesOfColours(Product newProduct) {
        newProduct.setColours(colourService.getColoursById(newProduct.getColours().stream().map(Colour::getId).toList()));
    }
    private void setValuesOfSizes(Product newProduct) {newProduct.setSizes(sizeService.getSizesById(newProduct.getSizes().stream().map(Size::getId).toList()));;
    }
    private void setValuesOfMaterials(Product newProduct) {
        newProduct.setMaterials(materialService.getMaterialsById(newProduct.getMaterials().stream().map(Material::getId).toList()));
    }

    // METODA WYKORZYSTYWANA POPRZEDNIO, KIEDY POLA MATERIALS, SIZES I COLOURS BYLY WYMAGANE DO STWORZENIA PRODUKTU
    private void setValuesOfColourMaterialSize(Product newProduct) {
        newProduct.setColours(colourService.getColoursById(newProduct.getColours().stream().map(Colour::getId).toList()));
        newProduct.setSizes(sizeService.getSizesById(newProduct.getSizes().stream().map(Size::getId).toList()));
        newProduct.setMaterials(materialService.getMaterialsById(newProduct.getMaterials().stream().map(Material::getId).toList()));
    }

     public Product getProductById(Long id) {
        return productRepository
                .findById(id)
                .orElseThrow(() -> new ObjectNotFoundInDBException("Nie znaleziono w bazie produktu o numerze id: " + id + "."));
    }

    // TODO co zwracać w tej metodzie? void czy Product? + czy zrobić sprawdzenie updatedInfo.id == productInDBId?
    void updateProduct(Product updatedInfo) {
        Product productInDB = getProductById(updatedInfo.getId());

//        System.out.println("productInDB price: " + productInDB.getPrice());
//        System.out.println("productSend price: " + updatedInfo.getPrice());

        if (updatedInfo.getVersion() != productInDB.getVersion()) {
            throw new ObjectVersionInconsistentException("Produkt został zaaktualizowany przez innego użytkownika. Odśwież stronę i spróbuj ponownie.");
        }

        // archiwizacja zmian produktu
        productChangesService.archiveProductChanges(getProductById(updatedInfo.getId()), updatedInfo);

        // zapisanie zmian w bazie
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