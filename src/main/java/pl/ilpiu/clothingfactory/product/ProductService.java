package pl.ilpiu.clothingfactory.product;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import pl.ilpiu.clothingfactory.exception.ObjectNotFoundInDBException;
import pl.ilpiu.clothingfactory.material.Material;
import pl.ilpiu.clothingfactory.material.MaterialService;
import pl.ilpiu.clothingfactory.product.colour.Colour;
import pl.ilpiu.clothingfactory.product.colour.ColourService;
import pl.ilpiu.clothingfactory.product.size.Size;
import pl.ilpiu.clothingfactory.product.size.SizeService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
class ProductService {

    private final ProductRepository productRepository;
    private final MaterialService materialService;
    private final ColourService colourService;
    private final SizeService sizeService;

//    List<Product> getAllProducts() {
//        return productRepository
//                .findAll();
//    }

    List<ProductBasicDetailsDTO> getAllProducts() {
        return productRepository.findAll().stream().map(ProductBasicDetailsDTO::new).collect(Collectors.toList());
    }

//    List<ProductBasicDetailsDTO> getAllProducts() {
//        return productRepository.findAll().stream().map(product ->
//                (ProductBasicDetailsDTO) product
//        ).collect(Collectors.toList());
//    }

    List<Product> getAllProducts(Pageable page) {
        return productRepository
                .findAll(page)
                .getContent();
    }

    // TODO zmienic nazwe wyniesionej metody
    Product createProduct(Product newProduct) {
        setProductSizeAndColour(newProduct);
        return productRepository
                .save(newProduct);
    }

    private void setProductSizeAndColour(Product newProduct) {
        newProduct.setColours(colourService.getColoursById(newProduct.getColours().stream().map(Colour::getId).toList()));
        newProduct.setSizes(sizeService.getSizesById(newProduct.getSizes().stream().map(Size::getId).toList()));
//        newProduct.setMaterial(materialService.getMaterialsById(newProduct.getMaterial().getId()));
        newProduct.setMaterials(materialService.getMaterialsById(newProduct.getMaterials().stream().map(Material::getId).toList()));
    }

    // TODO dodać walidację, jeżeli w repo nie będzie tego materiału, jakiś Optional np.
//    Product createProduct(Product newProduct) {
//        newProduct.setMaterial(materialService.getMaterialById(newProduct.getMaterial().getId()));
//        System.out.println(newProduct.getMaterial());
//        return productRepository
//                .save(newProduct);
//    }

    Product getProductById(Long id) {
        return productRepository
                .findById(id)
                .orElseThrow(() -> new ObjectNotFoundInDBException("Nie znaleziono w bazie produktu o numerze id: " + id + "."));
    }

    void updateProduct(Long id, Product updatedInfo) {
        productRepository.save(update(getProductById(id), updatedInfo));
    }

    Product update(Product toUpdate, final Product updatedInfo) {

        toUpdate.setName(updatedInfo.getName());
        toUpdate.setDescription(updatedInfo.getDescription());
        toUpdate.setAdditionalInformation(updatedInfo.getAdditionalInformation());
        toUpdate.setCategory(updatedInfo.getCategory());
        toUpdate.setMaterials(updatedInfo.getMaterials());
        toUpdate.setSizes(updatedInfo.getSizes());
        toUpdate.setColours(updatedInfo.getColours());
//        toUpdate.setPrices(updatedInfo.getPrices());
        toUpdate.setMaterialUsage(updatedInfo.getMaterialUsage());
        toUpdate.setUnitUsage(updatedInfo.getUnitUsage());
        return toUpdate;
    }

}
