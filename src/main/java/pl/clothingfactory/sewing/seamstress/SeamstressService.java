package pl.clothingfactory.sewing.seamstress;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.clothingfactory.exception.ObjectNotFoundInDBException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SeamstressService {

    private final SeamstressRepository seamstressRepository;


    List<Seamstress> getAllSeamstresses() {
        return seamstressRepository
                .findAll();
    }

    Seamstress createSeamstress(Seamstress newSeamstress) {
        return seamstressRepository
                .save(newSeamstress);
    }

     public Seamstress getSeamstressById(Long id) {
        return seamstressRepository
                .findById(id)
                .orElseThrow(() -> new ObjectNotFoundInDBException("Seamstress with id: " + id + " was not found."));
    }

    void updateSeamstress(Long id, Seamstress updatedInfo) {
        seamstressRepository.save(update(getSeamstressById(id), updatedInfo));
    }

    Seamstress update(Seamstress toUpdate, final Seamstress updatedInfo) {
        toUpdate.setName(updatedInfo.getName());
        return toUpdate;
    }
}
