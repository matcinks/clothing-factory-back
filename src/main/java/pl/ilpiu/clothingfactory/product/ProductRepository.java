package pl.ilpiu.clothingfactory.product;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface ProductRepository extends JpaRepository<Product, Long> {

    // dynamiczna metoda - dopasowuje projekcje do zwracania widoków
    <T> List<T> findAllBy(Class<T> projection);

}
