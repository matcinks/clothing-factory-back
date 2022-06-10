package pl.ilpiu.clothingfactory.product;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
//@CrossOrigin
@RequestMapping("/product")
class ProductController {

    // TODO add logic for adding new price, new colour, new size and updating rest of data

    private final ProductService productService;

    @GetMapping(params = {"!sort", "!page", "!size"})
    @ResponseBody
    List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping
    @ResponseBody
    List<Product> getAllProducts(Pageable page) {
        return productService.getAllProducts(page);
    }

    @GetMapping("/{id}")
    @ResponseBody
    Product getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @PostMapping
    @ResponseBody
    Product addNewProduct(@Valid @RequestBody Product newProduct){
        return productService.createProduct(newProduct);
    }

//    @PostMapping
//    @ResponseBody
//    Product addNewProduct(@Valid @RequestBody Product newProduct){
//        System.out.println(newProduct.getMaterial().getId());
//        Long materialId = Long.valueOf(newProduct.getMaterial().getId());
//        return productService.createProduct(newProduct, materialId);
//    }

    @PutMapping("/{id}")
    @ResponseBody
    void updateProduct(@PathVariable Long id, @Valid @RequestBody Product updatedInfo){
        productService.updateProduct(id, updatedInfo);
    }

    @GetMapping("/price/{id}")
    @ResponseBody
    BigDecimal getPrice(@PathVariable Long id){
        return productService.getPriceByProductId(id);
    }



}
