package pl.ilpiu.clothingfactory.sewing;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sewing-schedule")
class SewingScheduleController {

    private final SewingScheduleService sewingScheduleService;

    @GetMapping(params = {"!sort", "!page", "!size"})
    @ResponseBody
    List<SewingSchedule> getAllSewingEntries() {
        return sewingScheduleService.getAllSewingEntries();
    }

    @GetMapping
    @ResponseBody
    List<SewingSchedule> getAllSewingEntries(Pageable page) {
        return sewingScheduleService.getAllSewingEntries(page);
    }

    @GetMapping("/{id}")
    @ResponseBody
    SewingSchedule getSewingEntryById(@PathVariable Long id) {
        return sewingScheduleService.getSewingEntryById(id);
    }

    @PostMapping
    @ResponseBody
    SewingSchedule addNewSewingEntry(@Valid @RequestBody SewingSchedule newSewingEntry){
        return sewingScheduleService.createSewingEntry(newSewingEntry);
    }

}
