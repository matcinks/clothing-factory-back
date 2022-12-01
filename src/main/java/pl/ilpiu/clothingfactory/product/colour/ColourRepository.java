package pl.ilpiu.clothingfactory.product.colour;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ColourRepository extends JpaRepository<Colour, Long> {
    List<Colour> findAllByIdIn (List <Long> id);
}
