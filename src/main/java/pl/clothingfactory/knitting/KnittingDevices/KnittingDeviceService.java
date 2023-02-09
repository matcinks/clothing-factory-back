package pl.clothingfactory.knitting.KnittingDevices;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.clothingfactory.exception.ObjectNotFoundInDBException;

import java.util.List;

@Service
@RequiredArgsConstructor
class KnittingDeviceService {

    private final KnittingDeviceRepository knittingDeviceRepository;


    List<KnittingDevice> getAllKnittingDevices() {
        return knittingDeviceRepository
                .findAll();
    }

    List<KnittingDevice> getAllKnittingDevices(Pageable page) {
        return knittingDeviceRepository
                .findAll(page)
                .getContent();
    }

    KnittingDevice createKnittingDevice(KnittingDevice newKnittingDevice) {
        return knittingDeviceRepository
                .save(newKnittingDevice);
    }

    KnittingDevice getKnittingDeviceById(Long id) {
        return knittingDeviceRepository
                .findById(id)
                .orElseThrow(() -> new ObjectNotFoundInDBException("Knitting device with id: " + id + " was not found."));
    }

    void updateKnittingDevice(Long id, KnittingDevice updatedInfo) {
        knittingDeviceRepository.save(update(getKnittingDeviceById(id), updatedInfo));
    }

    // TODO Add knitting device updating info
    KnittingDevice update(KnittingDevice toUpdate, final KnittingDevice updatedInfo) {

        return toUpdate;
    }

    void deleteKnittingDevice(final Long id) {
        knittingDeviceRepository.delete(getKnittingDeviceById(id));
    }

}
