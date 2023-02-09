package pl.clothingfactory.knitting.KnittingDevices;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface KnittingDeviceRepository extends JpaRepository<KnittingDevice, Long> {
}
