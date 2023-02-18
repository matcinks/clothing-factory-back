package pl.clothingfactory.material;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/material")
class MaterialController {

    private final MaterialService materialService;

    @GetMapping(params = {"!sort", "!page", "!size"})
    @ResponseBody
    List<Material> getAllMaterials() {
        return materialService.getAllMaterials();
    }

    @GetMapping("/{id}")
    @ResponseBody
    Material getMaterialById(@PathVariable Long id) {
        return materialService.getMaterialById(id);
    }

    @PostMapping
    @ResponseBody
    Material addNewMaterial(@Valid @RequestBody Material newMaterial){
        return materialService.createMaterial(newMaterial);
    }

    @PutMapping
    @ResponseBody
    void updateMaterial( @Valid @RequestBody Material updatedInfo){
        materialService.updateMaterial(updatedInfo);
    }

    @GetMapping("/rawmaterial")
    @ResponseBody
    List<RawMaterial> getAllRawMaterials() {
        return materialService.getAllRawMaterials();
    }

}
