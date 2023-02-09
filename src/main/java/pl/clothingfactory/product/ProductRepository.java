package pl.clothingfactory.product;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.clothingfactory.product.projections.ProductMaterials;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // dynamiczna metoda - dopasowuje projekcje do zwracania widoków
    <T> List<T> findAllBy(Class<T> projection);

    // dynamiczna metoda - dopasowuje projekcje do zwracania widoków + filtrowanie po kategoriach
    <T> List<T> findAllByAndCategoryIn (Class<T> projection, List<Category> categories);

    // dynamiczna metoda - dopasowuje projekcje do zwracania widoków + filtrowanie po kategoriach + filtrowanie po nazwie
    <T> List<T> findAllByAndCategoryInAndNameContainingIgnoreCase(Class<T> projection, List<Category> categories, String name);

    // dynamiczna metoda - dopasowuje projekcje do zwracania widoków + filtrowanie po kategoriach + filtrowanie po nazwie
    <T> List<T> findAllByAndNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(Class<T> projection, String name, String additionalInformation );

    // dynamiczna metoda - dopasowuje projekcje do zwracania widoków + filtrowanie po nazwie
    <T> List<T> findAllByAndName(Class<T> projection, String name);

    // dynamiczna metoda - dopasowuje projekcje do zwracania widoków + filtrowanie po kategoriach + filtrowanie po id
    <T> List<T> findAllByAndCategoryInAndId(Class<T> projection, List<Category> categories, Long id);

    // dynamiczna metoda - dopasowuje projekcje do zwracania widoków + filtrowanie po id
    <T> List<T> findAllByAndId (Class<T> projection, Long id);

    ProductMaterials findAllById(Long id);

    boolean existsById(Long id);
}
