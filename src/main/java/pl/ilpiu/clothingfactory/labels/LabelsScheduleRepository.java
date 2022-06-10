package pl.ilpiu.clothingfactory.labels;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LabelsScheduleRepository extends JpaRepository<LabelsSchedule, Long> {
}
