package pl.ilpiu.clothingfactory.material;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.ilpiu.clothingfactory.exception.MaterialCompositionExceededException;
import pl.ilpiu.clothingfactory.exception.ObjectNotFoundInDBException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MaterialService {

    private final MaterialRepository materialRepository;

    List<Material> getAllMaterials() {
        return materialRepository
                .findAll();
    }

    List<Material> getAllMaterials(Pageable page) {
        return materialRepository
                .findAll(page)
                .getContent();
    }

    Material createMaterial(Material newMaterial) {

        Integer compositionSummary = newMaterial.getComposition()
                .stream()
                .map(Composition::getPercentage)
                .reduce(0, Integer::sum);

        if (compositionSummary != 100) {
            throw new MaterialCompositionExceededException("Material composition summary should be equals 100.");
        }

        return materialRepository
                .save(newMaterial);
    }

    public Material getMaterialById(Long id) {
        return materialRepository
                .findById(id)
                .orElseThrow(() -> new ObjectNotFoundInDBException("Material with id: " + id + " was not found."));
    }

    void updateMaterial(Long id, Material updatedInfo) {
        materialRepository.save(update(getMaterialById(id), updatedInfo));
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


    // TODO create update for material
    // PATCH or PUT? Maybe update only some fields?
//    void updateMaterialPartially(Long id, Map<Object, Object> valuesToUpdate) {
//        Material material = getMaterialById(id);
//
//        valuesToUpdate.forEach();
//
//    }
}
