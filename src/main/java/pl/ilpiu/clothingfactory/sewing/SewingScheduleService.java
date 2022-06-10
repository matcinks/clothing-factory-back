package pl.ilpiu.clothingfactory.sewing;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.ilpiu.clothingfactory.exception.MaterialCompositionExceededException;
import pl.ilpiu.clothingfactory.exception.ObjectNotFoundInDBException;
import pl.ilpiu.clothingfactory.material.Composition;
import pl.ilpiu.clothingfactory.material.Material;

import java.util.List;

@Service
@RequiredArgsConstructor
class SewingScheduleService {

    private final SewingScheduleRepository sewingScheduleRepository;

    List<SewingSchedule> getAllSewingEntries() {
        return sewingScheduleRepository
                .findAll();
    }

    List<SewingSchedule> getAllSewingEntries(Pageable page) {
        return sewingScheduleRepository
                .findAll(page)
                .getContent();
    }

    SewingSchedule createSewingEntry(SewingSchedule newSewingEntry) {
        return sewingScheduleRepository
                .save(newSewingEntry);
    }

    SewingSchedule getSewingEntryById(Long id) {
        return sewingScheduleRepository
                .findById(id)
                .orElseThrow(() -> new ObjectNotFoundInDBException("Sewing entry with id: " + id + " was not found."));
    }

}
