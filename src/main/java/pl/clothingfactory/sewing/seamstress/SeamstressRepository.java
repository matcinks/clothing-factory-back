package pl.clothingfactory.sewing.seamstress;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface SeamstressRepository extends JpaRepository<Seamstress, Long> {
}
