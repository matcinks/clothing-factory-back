package pl.clothingfactory.cutting;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
interface CuttingScheduleRepository extends JpaRepository<CuttingSchedule, Long> {

}