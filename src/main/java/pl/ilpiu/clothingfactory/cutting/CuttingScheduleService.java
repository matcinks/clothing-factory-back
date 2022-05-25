package pl.ilpiu.clothingfactory.cutting;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class CuttingScheduleService {
    private CuttingScheduleRepository repo;
}
