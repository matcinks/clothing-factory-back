package pl.ilpiu.clothingfactory.product.colour;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import pl.ilpiu.clothingfactory.material.Material;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/colour")
class ColourController {

    private final ColourService colourService;


    @GetMapping(params = {"!sort", "!page", "!size"})
    @ResponseBody
    List<Colour> getAllColours() {
        return colourService.getAllColours();
    }

    @GetMapping
    @ResponseBody
    List<Colour> getAllColours(Pageable page) {
        return colourService.getAllColours(page);
    }

    @GetMapping("/{id}")
    @ResponseBody
    Colour getColourById(@PathVariable Long id) {
        return colourService.getColourById(id);
    }

    @PostMapping
    @ResponseBody
    Colour addNewColour(@Valid @RequestBody Colour newColour){
        return colourService.createColour(newColour);
    }

    @PutMapping("/{id}")
    @ResponseBody
    void updateColour(@PathVariable Long id, @Valid @RequestBody Colour updatedInfo){
        colourService.updateColour(id, updatedInfo);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    void deleteColour(@PathVariable Long id){
        colourService.deleteColour(id);
    }

}
