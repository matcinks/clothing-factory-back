package pl.ilpiu.clothingfactory.material;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface MaterialRepository extends JpaRepository<Material, Long> {
}
