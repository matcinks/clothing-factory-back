package pl.clothingfactory.sewing.seamstress;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/seamstress")
class SeamstressController {

    private final SeamstressService seamstressService;

    @GetMapping(params = {"!sort", "!page", "!size"})
    @ResponseBody
    List<Seamstress> getAllSeamstresses() {
        return seamstressService.getAllSeamstresses();
    }

    @GetMapping
    @ResponseBody
    List<Seamstress> getAllSeamstresses(Pageable page) {
        return seamstressService.getAllSeamstresses(page);
    }

    @GetMapping("/{id}")
    @ResponseBody
    Seamstress getSeamstressById(@PathVariable Long id) {
        return seamstressService.getSeamstressById(id);
    }

    @PostMapping
    @ResponseBody
    Seamstress addNewSeamstress(@Valid @RequestBody Seamstress newSeamstress){
        return seamstressService.createSeamstress(newSeamstress);
    }

    @PutMapping("/{id}")
    @ResponseBody
    void updateSeamstress(@PathVariable Long id, @Valid @RequestBody Seamstress updatedInfo){
        seamstressService.updateSeamstress(id, updatedInfo);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    void deleteSeamstress(@PathVariable Long id){
        seamstressService.deleteSeamstress(id);
    }

}
