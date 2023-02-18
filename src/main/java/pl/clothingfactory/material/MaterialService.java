package pl.clothingfactory.material;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.clothingfactory.exception.MaterialCompositionExceededException;
import pl.clothingfactory.exception.ObjectNotFoundInDBException;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MaterialService {

    private final MaterialRepository materialRepository;

    List<Material> getAllMaterials() {
        return materialRepository
                .findAll();
    }

    Material createMaterial(Material newMaterial) {

        Integer compositionSummary = newMaterial.getComposition()
                .stream()
                .map(Composition::getPercentage)
                .reduce(0, Integer::sum);

        if (compositionSummary != 100) {
            throw new MaterialCompositionExceededException("Suma składników w materiale musi wynosić 100%.");
        }

        return materialRepository
                .save(newMaterial);
    }

    public Material getMaterialById(Long id) {
        return materialRepository
                .findById(id)
                .orElseThrow(() -> new ObjectNotFoundInDBException("W bazie danych nie znaleziono materiału o numerze id: " + id));
    }

        public List<Material> getMaterialsById(List<Long> idList) {
        List <Material> materialsList = materialRepository.findAllByIdIn(idList);

        List <Long> materialsListId = materialsList.stream().map(Material::getId).toList();
        List <Long> notFoundIds = idList.stream().filter(id -> !materialsListId.contains(id)).toList();
            if (!notFoundIds.isEmpty()) throw new ObjectNotFoundInDBException("W bazie danych nie znaleziono materiałów o numerach id:  "
                    + notFoundIds.stream().map(Object::toString).collect(Collectors.joining(", ")));
        return materialsList;
    }

    void updateMaterial(Material updatedInfo) {
        materialRepository.save(update(getMaterialById(updatedInfo.getId()), updatedInfo));
    }

    Material update(Material toUpdate, final Material updatedInfo) {
        toUpdate.setName(updatedInfo.getName());
        toUpdate.setAdditionalDescription(updatedInfo.getAdditionalDescription());
        toUpdate.setComposition(updatedInfo.getComposition());
        toUpdate.setPrice(updatedInfo.getPrice());
        toUpdate.setPriceUnit(updatedInfo.getPriceUnit());
        toUpdate.createdAt();
        return toUpdate;
    }

    List<RawMaterial> getAllRawMaterials() {
        return Arrays.asList(RawMaterial.values());
    }
}
