package pl.ilpiu.clothingfactory.sewing;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class SewingScheduleService {
    private final SewingScheduleRepository repo;
}
