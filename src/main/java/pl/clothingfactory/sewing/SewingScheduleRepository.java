package pl.clothingfactory.sewing;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
interface SewingScheduleRepository extends JpaRepository<SewingSchedule, Long> {
    List<SewingScheduleBasicInfo> findAllBy();
    List<SewingScheduleBasicInfo> findAllByScheduledOn(Date date);
}
