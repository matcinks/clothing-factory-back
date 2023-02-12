package pl.clothingfactory.cutting;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cutting-schedule")
public class CuttingScheduleController {

    private final CuttingScheduleService cuttingScheduleService;

    @GetMapping(params = {"!sort", "!page", "!size"})
    @ResponseBody
    List<CuttingSchedule> getAllCuttingEntries() {
        return cuttingScheduleService.getAllCuttingEntries();
    }

    @GetMapping
    @ResponseBody
    List<CuttingSchedule> getAllCuttingEntries(Pageable page) {
        return cuttingScheduleService.getAllCuttingEntries(page);
    }

    @GetMapping("/{id}")
    @ResponseBody
    CuttingSchedule getCuttingEntryById(@PathVariable Long id) {
        return cuttingScheduleService.getCuttingEntryById(id);
    }

    @PostMapping
    @ResponseBody
    CuttingSchedule addNewCuttingEntry(@Valid @RequestBody CuttingSchedule newCuttingEntry){
        return cuttingScheduleService.createCuttingEntry(newCuttingEntry);
    }

    /*
    private final CuttingScheduleService cuttingScheduleService;

    // TODO obudowac response entity
    @PostMapping
    public CuttingSchedule createCuttingSchedule(@RequestBody CuttingSchedule cuttingSchedule){

        cuttingSchedule.getProduct().getId(); // to jest to co postmanie
        return cuttingScheduleService.createCuttingSchedule(cuttingSchedule);
    }

    @GetMapping
    public String getTest(){
        return "Hello from Cutting Schedule!";
    }
*/
}
