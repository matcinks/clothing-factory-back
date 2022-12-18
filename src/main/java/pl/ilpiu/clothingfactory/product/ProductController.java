package pl.ilpiu.clothingfactory.product;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import pl.ilpiu.clothingfactory.product.projections.ProductDetailsBasicInfo;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
class ProductController {

    // TODO add logic for adding new price, new colour, new size and updating rest of data

    private final ProductService productService;

    @GetMapping(params = {"!sort", "!page", "!size"})
    List<ProductDetailsBasicInfo> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping
    List<Product> getAllProducts(Pageable page) {
        return productService.getAllProducts(page);
    }

    @GetMapping("/{id}")
    Product getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @PostMapping
    Product addNewProduct(@Valid @RequestBody Product newProduct){
        return productService.createProduct(newProduct);
    }

    @PutMapping
    void updateProduct(@Valid @RequestBody Product updatedInfo){
        productService.updateProduct(updatedInfo);
    }
}
