package pl.clothingfactory.product;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import pl.clothingfactory.product.projections.ProductDetailsBasicInfo;
import pl.clothingfactory.product.projections.ProductMaterials;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
class ProductController {

    // TODO add logic for adding new price, new colour, new size and updating rest of data

    private final ProductService productService;

    @GetMapping(params = {"!sort", "!page", "!size"})
    List<ProductDetailsBasicInfo> getAllProducts(@RequestParam(name = "category", required = false) List<Category> checkedCategories, @RequestParam(name = "search", required = false) String search) {
        return productService.getAllProducts(checkedCategories, search);
    }

    @GetMapping
    List<Product> getAllProducts(Pageable page) {
        return productService.getAllProducts(page);
    }

    @GetMapping("/{id}")
    Product getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @GetMapping("/category")
    List<Category> getAllCategories() {
        return productService.getAllCategories();
    }

    @PostMapping
    Product addNewProduct(@Valid @RequestBody Product newProduct) {
        return productService.createProduct(newProduct);
    }

    @PutMapping
    void updateProduct(@Valid @RequestBody Product updatedInfo) {
        productService.updateProduct(updatedInfo);
    }

    @GetMapping("/{id}/materials")
    ProductMaterials getProductMaterials(@PathVariable Long id) {
        return productService.getProductMaterials(id);
    }
}
