package pl.clothingfactory.product;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.clothingfactory.product.projections.ProductDetailsBasicInfo;
import pl.clothingfactory.product.projections.ProductMaterials;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
class ProductController {

    private final ProductService productService;

    @GetMapping(params = {"!sort", "!page", "!size"})
    @PreAuthorize("hasAuthority('KIEROWNIK')")
    List<ProductDetailsBasicInfo> getAllProducts(@RequestParam(name = "category", required = false) List<Category> checkedCategories, @RequestParam(name = "search", required = false) String search) {
        return productService.getAllProducts(checkedCategories, search);
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
