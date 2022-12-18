package pl.ilpiu.clothingfactory.product.archive;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface ProductChangesRepository extends JpaRepository<ProductChanges, Long> {
    List<ProductChanges> findAllByProductId(Long productId);

}
