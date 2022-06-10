package pl.ilpiu.clothingfactory.product.colour;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.ilpiu.clothingfactory.exception.ObjectNotFoundInDBException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public
class ColourService {

    private final ColourRepository colourRepository;


    List<Colour> getAllColours() {
        return colourRepository
                .findAll();
    }

    List<Colour> getAllColours(Pageable page) {
        return colourRepository
                .findAll(page)
                .getContent();
    }

    Colour createColour(Colour newColour) {
        return colourRepository
                .save(newColour);
    }

    Colour getColourById(Long id) {
        return colourRepository
                .findById(id)
                .orElseThrow(() -> new ObjectNotFoundInDBException("Colour with id: " + id + " was not found."));
    }

    // TODO walidacja dla pustej listy
    public List <Colour> getColoursById(List<Long> idList) {
        return colourRepository.findAllByIdIn(idList);
    }

    void updateColour(Long id, Colour updatedInfo) {
        colourRepository.save(update(getColourById(id), updatedInfo));
    }

    Colour update(Colour toUpdate, final Colour updatedInfo) {
        toUpdate.setName(updatedInfo.getName());
        toUpdate.createdAt();
        return toUpdate;
    }

    void deleteColour(final Long id) {
        colourRepository.delete(getColourById(id));
    }
}
