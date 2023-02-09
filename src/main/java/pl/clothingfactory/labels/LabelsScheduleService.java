package pl.clothingfactory.labels;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.clothingfactory.exception.ObjectNotFoundInDBException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LabelsScheduleService {

    private final LabelsScheduleRepository labelsScheduleRepository;

    List<LabelsSchedule> getAllLabelsEntries() {
        return labelsScheduleRepository
                .findAll();
    }

    List<LabelsSchedule> getAllLabelsEntries(Pageable page) {
        return labelsScheduleRepository
                .findAll(page)
                .getContent();
    }

    LabelsSchedule createLabelEntry(LabelsSchedule newKnittingEntry) {
        return labelsScheduleRepository
                .save(newKnittingEntry);
    }

    LabelsSchedule getLabelEntryById(Long id) {
        return labelsScheduleRepository
                .findById(id)
                .orElseThrow(() -> new ObjectNotFoundInDBException("Label entry with id: " + id + " was not found."));
    }

    /*

    // TODO rozszerzyc o dodawanie typu metki lub obu od razu
    // TODO W TYM ROZWIAZANIU NIE PRZEKAZUJE TYPU METKI!!

    public LabelsSchedule addLabelsSchedule(LabelsSchedule schedule){
//        LabelsSchedule lbs = (LabelsSchedule) schedule;
//        lbs.setLabelsType(LabelsType.PRICE);
        return labelsScheduleRepository.save(schedule);
    }
     */

}
