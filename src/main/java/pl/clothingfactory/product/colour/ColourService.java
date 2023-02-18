package pl.clothingfactory.product.colour;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.clothingfactory.exception.ObjectNotFoundInDBException;

import java.util.List;

@Service
@RequiredArgsConstructor
public
class ColourService {

    private final ColourRepository colourRepository;


    List<Colour> getAllColours() {
        return colourRepository
                .findAll();
    }

    Colour createColour(Colour newColour) {
        return colourRepository
                .save(newColour);
    }

    public Colour getColourById(Long id) {
        return colourRepository
                .findById(id)
                .orElseThrow(() -> new ObjectNotFoundInDBException("Nie znaleziono w bazie danych koloru o numerze id: " + id));
    }

    public List<Colour> getColoursById(List<Long> idList) {
        List <Colour> coloursList = colourRepository.findAllByIdIn(idList);
        if (coloursList.isEmpty()) throw new ObjectNotFoundInDBException("Nie znaleziono wybranych kolorów w bazie danych.");
        return coloursList;
    }

    void updateColour(Long id, Colour updatedInfo) {
        colourRepository.save(update(getColourById(id), updatedInfo));
    }

    Colour update(Colour toUpdate, final Colour updatedInfo) {
        toUpdate.setName(updatedInfo.getName());
        toUpdate.createdAt();
        return toUpdate;
    }
}
