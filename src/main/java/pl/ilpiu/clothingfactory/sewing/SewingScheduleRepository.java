package pl.ilpiu.clothingfactory.sewing;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface SewingScheduleRepository extends JpaRepository<SewingSchedule, Long> {
}
