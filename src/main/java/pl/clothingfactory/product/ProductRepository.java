package pl.clothingfactory.product;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.clothingfactory.product.projections.ProductMaterials;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    <T> List<T> findAllBy(Class<T> projection);

    <T> List<T> findAllByAndCategoryIn (Class<T> projection, List<Category> categories);

    <T> List<T> findAllByAndCategoryInAndNameContainingIgnoreCase(Class<T> projection, List<Category> categories, String name);

    <T> List<T> findAllByAndNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(Class<T> projection, String name, String additionalInformation );

    <T> List<T> findAllByAndName(Class<T> projection, String name);

    <T> List<T> findAllByAndCategoryInAndId(Class<T> projection, List<Category> categories, Long id);

    <T> List<T> findAllByAndId (Class<T> projection, Long id);

    ProductMaterials findAllById(Long id);

    boolean existsById(Long id);
}
