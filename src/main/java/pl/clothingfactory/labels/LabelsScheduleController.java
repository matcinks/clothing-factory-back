package pl.clothingfactory.labels;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/labels-schedule")
class LabelsScheduleController {

    private final LabelsScheduleService labelsScheduleService;

    @GetMapping(params = {"!sort", "!page", "!size"})
    @ResponseBody
    List<LabelsSchedule> getAllLabelsEntries() {
        return labelsScheduleService.getAllLabelsEntries();
    }

    @GetMapping
    @ResponseBody
    List<LabelsSchedule> getAllLabelsEntries(Pageable page) {
        return labelsScheduleService.getAllLabelsEntries(page);
    }

    @GetMapping("/{id}")
    @ResponseBody
    LabelsSchedule getLabelEntryById(@PathVariable Long id) {
        return labelsScheduleService.getLabelEntryById(id);
    }

    @PostMapping
    @ResponseBody
    LabelsSchedule addNewLabelEntry(@Valid @RequestBody LabelsSchedule newLabelEntry){
        return labelsScheduleService.createLabelEntry(newLabelEntry);
    }

}
