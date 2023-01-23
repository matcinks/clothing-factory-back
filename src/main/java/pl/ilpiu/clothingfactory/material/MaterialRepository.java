package pl.ilpiu.clothingfactory.material;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.ilpiu.clothingfactory.product.colour.Colour;

import java.util.List;

@Repository
interface MaterialRepository extends JpaRepository<Material, Long> {

    List<Material> findAllByIdIn (List <Long> id);
}
