package pl.clothingfactory.sewing;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sewing")
class SewingScheduleController {

    private final SewingScheduleService sewingScheduleService;

    @GetMapping(params = {"!sort", "!page", "!size"})
    List<SewingScheduleBasicInfo> getAllSewingBasicInfoEntries() {
        return sewingScheduleService.getAllSewingBasicInfoEntries();
    }

    @GetMapping("/{id}")
    SewingSchedule getSewingEntryById(@PathVariable Long id) {
        return sewingScheduleService.getSewingEntryById(id);
    }

    @PostMapping
    SewingSchedule addNewSewingEntry(@Valid @RequestBody SewingSchedule newSewingEntry){
        return sewingScheduleService.createSewingEntry(newSewingEntry);
    }

    @GetMapping(path = "/{date}",params = {"!sort", "!page", "!size"})
    List<SewingScheduleBasicInfo> getAllSewingBasicInfoEntries(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date) {
        return sewingScheduleService.getAllSewingBasicInfoEntries(date);
    }

    @PutMapping("/{id}")
    void updateSewingStatus(@PathVariable Long id, @Valid @RequestBody String newStatus) {
        sewingScheduleService.updateSewingStatus(id, newStatus);
    }

    @PutMapping
    void updateSewingEntry(@Valid @RequestBody SewingSchedule updatedInfo) {
        sewingScheduleService.updateSewingEntry(updatedInfo);
    }

}
