package pl.clothingfactory.knitting;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.clothingfactory.exception.ObjectNotFoundInDBException;

import java.util.List;

@Service
@RequiredArgsConstructor
class KnittingScheduleService {

    private final KnittingScheduleRepository knittingScheduleRepository;

    List<KnittingSchedule> getAllKnittingEntries() {
        return knittingScheduleRepository
                .findAll();
    }

    List<KnittingSchedule> getAllKnittingEntries(Pageable page) {
        return knittingScheduleRepository
                .findAll(page)
                .getContent();
    }

    KnittingSchedule createKnittingEntry(KnittingSchedule newKnittingEntry) {
        return knittingScheduleRepository
                .save(newKnittingEntry);
    }

    KnittingSchedule getKnittingEntryById(Long id) {
        return knittingScheduleRepository
                .findById(id)
                .orElseThrow(() -> new ObjectNotFoundInDBException("Knitting entry with id: " + id + " was not found."));
    }


    /*
    private final KnittingScheduleRepository repo;

    // TODO this method is supposed to change order on particular knitting device
    public void changeScheduleOrder(){

    }

    // TODO this method is supposed to add washing and price labels amounts to added
    public void addLabels(){

    }
    */


}
