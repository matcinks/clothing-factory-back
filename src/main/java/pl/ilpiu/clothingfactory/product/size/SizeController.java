package pl.ilpiu.clothingfactory.product.size;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import pl.ilpiu.clothingfactory.product.colour.Colour;

import javax.validation.Valid;
import java.util.List;

//@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/size")
class SizeController {

    private final SizeService sizeService;


    @GetMapping(params = {"!sort", "!page", "!size"})
    @ResponseBody
    List<Size> getAllSizes() {
        return sizeService.getAllSizes();
    }

    @GetMapping
    @ResponseBody
    List<Size> getAllSizes(Pageable page) {
        return sizeService.getAllSizes(page);
    }

    @GetMapping("/{id}")
    @ResponseBody
    Size getSizeById(@PathVariable Long id) {
        return sizeService.getSizeById(id);
    }

    @PostMapping
    @ResponseBody
    Size addNewSize(@Valid @RequestBody Size newSize){
        return sizeService.createSize(newSize);
    }

    @PutMapping("/{id}")
    @ResponseBody
    void updateSize(@PathVariable Long id, @Valid @RequestBody Size updatedInfo){
        sizeService.updateSize(id, updatedInfo);
    }

//    @DeleteMapping("/{id}")
//    @ResponseBody
//    void deleteSize(@PathVariable Long id){
//        sizeService.deleteSize(id);
//    }

}
