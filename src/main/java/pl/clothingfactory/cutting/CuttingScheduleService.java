package pl.clothingfactory.cutting;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.clothingfactory.exception.ObjectNotFoundInDBException;

import java.util.List;

@Service
@RequiredArgsConstructor
class CuttingScheduleService {

    private final CuttingScheduleRepository cuttingScheduleRepository;

    List<CuttingSchedule> getAllCuttingEntries() {
        return cuttingScheduleRepository
                .findAll();
    }

    List<CuttingSchedule> getAllCuttingEntries(Pageable page) {
        return cuttingScheduleRepository
                .findAll(page)
                .getContent();
    }

    CuttingSchedule createCuttingEntry(CuttingSchedule newCuttingEntry) {
        return cuttingScheduleRepository
                .save(newCuttingEntry);
    }

    CuttingSchedule getCuttingEntryById(Long id) {
        return cuttingScheduleRepository
                .findById(id)
                .orElseThrow(() -> new ObjectNotFoundInDBException("Cutting entry with id: " + id + " was not found."));
    }

    /*
    private final CuttingScheduleRepository repo;
    private final LabelsScheduleService labelsService;

    private final ProductRepository productRepository;

    // jak dodaje sobie nowa encje do cuttingschedule
    // to jednoczesnie dodaje sie encja do labels schedule

    public CuttingSchedule createCuttingSchedule(CuttingSchedule cuttingSchedule) {
//        labelsService.addLabelsSchedule(buildLabelsSchedule(cuttingSchedule));
        // TODO W TYM MIEJSCU DODAC INFORMACJE DO ARGUMENTOW METODY O TYPIE METKI
//        labelsService.addLabelsSchedule(cuttingSchedule);

//        cuttingSchedule.setProduct(getProductById(cuttingSchedule.getProduct().getId()));

        return repo.save(cuttingSchedule);
    }

    private Product getProductById(Long id){
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found for id: " + id));
    }
    */
}
