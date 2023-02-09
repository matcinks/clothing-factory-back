package pl.clothingfactory.knitting;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface KnittingScheduleRepository extends JpaRepository<KnittingSchedule, Long> {
}
