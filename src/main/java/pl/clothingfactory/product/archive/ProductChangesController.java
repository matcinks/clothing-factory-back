package pl.clothingfactory.product.archive;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/archive/product")
class ProductChangesController {

    private final ProductChangesService productChangesService;

    @GetMapping("/{id}")
    public List<ProductChanges> getAllProductChangesByProductId(@PathVariable(name = "id")  Long productId) {
        return productChangesService.getAllProductChangesByProductId(productId);
    }

}
