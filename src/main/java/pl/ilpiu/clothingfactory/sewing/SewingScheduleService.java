package pl.ilpiu.clothingfactory.sewing;

import lombok.RequiredArgsConstructor;
import org.apache.tomcat.jni.Local;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.ilpiu.clothingfactory.common.Schedule;
import pl.ilpiu.clothingfactory.common.Status;
import pl.ilpiu.clothingfactory.exception.ObjectNotFoundInDBException;

import pl.ilpiu.clothingfactory.material.MaterialService;
import pl.ilpiu.clothingfactory.product.ProductService;
import pl.ilpiu.clothingfactory.product.colour.ColourService;
import pl.ilpiu.clothingfactory.product.size.SizeService;
import pl.ilpiu.clothingfactory.sewing.seamstress.SeamstressService;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
class SewingScheduleService {

    private final ProductService productService;
    private final ColourService colourService;
    private final SizeService sizeService;

    private final SeamstressService seamstressService;
    private final SewingScheduleRepository sewingScheduleRepository;

    private final MaterialService materialService;

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
        newSewingEntry.setProduct(productService.getProductById(newSewingEntry.getProduct().getId()));
        newSewingEntry.setSize(sizeService.getSizeById(newSewingEntry.getSize().getId()));
        newSewingEntry.setColour(colourService.getColourById(newSewingEntry.getColour().getId()));
        newSewingEntry.setSeamstress(seamstressService.getSeamstressById(newSewingEntry.getSeamstress().getId()));
        return sewingScheduleRepository
                .save(newSewingEntry);
    }

    SewingSchedule getSewingEntryById(Long id) {
        return sewingScheduleRepository
                .findById(id)
                .orElseThrow(() -> new ObjectNotFoundInDBException("Sewing entry with id: " + id + " was not found."));
    }

    List<SewingScheduleBasicInfo> getAllSewingBasicInfoEntries() {
        return sewingScheduleRepository
                .findAllBy();
    }

    List<SewingScheduleBasicInfo> getAllSewingBasicInfoEntries(Date date) {
        return sewingScheduleRepository
                .findAllByScheduledOn(date);
    }

    void updateSewingStatus(final Long id, final String newStatus) {
        SewingSchedule sewingEntry = getSewingEntryById(id);
        sewingEntry.setStatus(Status.valueOf(newStatus));
        sewingScheduleRepository.save(sewingEntry);
    }

    void updateSewingEntry(final SewingSchedule updatedInfo) {
        updatedInfo.setProduct(productService.getProductById(updatedInfo.getProduct().getId()));
        updatedInfo.setSize(sizeService.getSizeById(updatedInfo.getSize().getId()));
        updatedInfo.setColour(colourService.getColourById(updatedInfo.getColour().getId()));
        updatedInfo.setSeamstress(seamstressService.getSeamstressById(updatedInfo.getSeamstress().getId()));
        updatedInfo.setMaterial(materialService.getMaterialById(updatedInfo.getMaterial().getId()));
        sewingScheduleRepository
                .save(updatedInfo);
    }
}
