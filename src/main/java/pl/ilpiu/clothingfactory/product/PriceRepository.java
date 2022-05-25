package pl.ilpiu.clothingfactory.product;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.ilpiu.clothingfactory.product.Price;

public interface PriceRepository extends JpaRepository<Price, Long> {
}
