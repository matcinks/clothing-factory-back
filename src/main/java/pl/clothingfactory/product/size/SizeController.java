package pl.clothingfactory.product.size;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

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
    @PreAuthorize("hasAnyAuthority('KIEROWNIK', 'BIURO')")
    void updateSize(@PathVariable Long id, @Valid @RequestBody Size updatedInfo){
        sizeService.updateSize(id, updatedInfo);
    }

}
