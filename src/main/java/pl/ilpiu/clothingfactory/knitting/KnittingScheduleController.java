package pl.ilpiu.clothingfactory.knitting;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/knitting-schedule")
class KnittingScheduleController {

    private final KnittingScheduleService knittingScheduleService;

    @GetMapping(params = {"!sort", "!page", "!size"})
    @ResponseBody
    List<KnittingSchedule> getAllKnittingEntries() {
        return knittingScheduleService.getAllKnittingEntries();
    }

    @GetMapping
    @ResponseBody
    List<KnittingSchedule> getAllKnittingEntries(Pageable page) {
        return knittingScheduleService.getAllKnittingEntries(page);
    }

    @GetMapping("/{id}")
    @ResponseBody
    KnittingSchedule getKnittingEntryById(@PathVariable Long id) {
        return knittingScheduleService.getKnittingEntryById(id);
    }

    @PostMapping
    @ResponseBody
    KnittingSchedule addNewKnittingEntry(@Valid @RequestBody KnittingSchedule newKnittingEntry){
        return knittingScheduleService.createKnittingEntry(newKnittingEntry);
    }

}
