package pl.clothingfactory.material;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface MaterialRepository extends JpaRepository<Material, Long> {

    List<Material> findAllByIdIn (List <Long> id);
}
