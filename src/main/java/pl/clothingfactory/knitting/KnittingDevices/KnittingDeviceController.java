package pl.clothingfactory.knitting.KnittingDevices;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/knitting-device")
class KnittingDeviceController {

    private final KnittingDeviceService knittingDeviceService;

    @GetMapping(params = {"!sort", "!page", "!size"})
    @ResponseBody
    List<KnittingDevice> getAllKnittingDevices() {
        return knittingDeviceService.getAllKnittingDevices();
    }

    @GetMapping
    @ResponseBody
    List<KnittingDevice> getAllKnittingDevices(Pageable page) {
        return knittingDeviceService.getAllKnittingDevices(page);
    }

    @GetMapping("/{id}")
    @ResponseBody
    KnittingDevice getKnittingDeviceById(@PathVariable Long id) {
        return knittingDeviceService.getKnittingDeviceById(id);
    }

    @PostMapping
    @ResponseBody
    KnittingDevice addNewKnittingDevice(@Valid @RequestBody KnittingDevice newKnittingDevice){
        return knittingDeviceService.createKnittingDevice(newKnittingDevice);
    }

    @PutMapping("/{id}")
    @ResponseBody
    void updateKnittingDevice(@PathVariable Long id, @Valid @RequestBody KnittingDevice updatedInfo){
        knittingDeviceService.updateKnittingDevice(id, updatedInfo);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    void deleteKnittingDevice(@PathVariable Long id){
        knittingDeviceService.deleteKnittingDevice(id);
    }


}
