package pl.ilpiu.clothingfactory.product.size;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.ilpiu.clothingfactory.exception.ObjectNotFoundInDBException;
import pl.ilpiu.clothingfactory.product.colour.Colour;

import java.util.List;

@Service
@RequiredArgsConstructor
public
class SizeService {

    private final SizeRepository sizeRepository;


    List<Size> getAllSizes() {
        return sizeRepository
                .findAll();
    }

    List<Size> getAllSizes(Pageable page) {
        return sizeRepository
                .findAll(page)
                .getContent();
    }

    Size createSize(Size newSize) {
        return sizeRepository
                .save(newSize);
    }

    public Size getSizeById(Long id) {
        return sizeRepository
                .findById(id)
                .orElseThrow(() -> new ObjectNotFoundInDBException("Colour with id: " + id + " was not found."));
    }

    // TODO dodac walidacje listy jesli nie znajdzie rozmiaru
    public List<Size> getSizesById(List<Long> idList) {
        return sizeRepository.findAllByIdIn(idList);
    }

    void updateSize(Long id, Size updatedInfo) {
        sizeRepository.save(update(getSizeById(id), updatedInfo));
    }

    Size update(Size toUpdate, final Size updatedInfo) {
        toUpdate.setName(updatedInfo.getName());
        toUpdate.createdAt();
        return toUpdate;
    }

    void deleteSize(final Long id) {
        sizeRepository.delete(getSizeById(id));
    }


}
