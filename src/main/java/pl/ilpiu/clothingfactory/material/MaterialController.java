package pl.ilpiu.clothingfactory.material;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @GetMapping
    @ResponseBody
    List<Material> getAllMaterials(Pageable page) {
        return materialService.getAllMaterials(page);
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

    @PutMapping("/{id}")
    @ResponseBody
    void updateMaterial(@PathVariable Long id, @Valid @RequestBody Material updatedInfo){
        materialService.updateMaterial(id, updatedInfo);
    }


//    @PatchMapping("/{id}")
//    @ResponseBody
//    void updateMaterialPartially(@PathVariable Long id, @RequestBody Map<Object, Object> valuesToUpdate){
//        materialService.updateMaterialPartially(id, valuesToUpdate);
//    }




}
