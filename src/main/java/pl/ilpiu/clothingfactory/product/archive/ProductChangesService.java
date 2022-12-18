package pl.ilpiu.clothingfactory.product.archive;

import com.fasterxml.jackson.annotation.JsonIgnoreType;
import lombok.val;
import org.apache.commons.lang3.StringUtils;
import org.javers.core.Changes;
import org.javers.core.Javers;

import org.javers.core.changelog.ChangeProcessor;
import org.javers.core.diff.Change;
import org.javers.core.diff.Diff;

import org.javers.core.diff.changetype.PropertyChange;
import org.javers.core.diff.changetype.ReferenceChange;
import org.javers.core.diff.changetype.ValueChange;

import org.javers.core.diff.changetype.container.ContainerChange;
import org.javers.core.diff.changetype.container.ContainerElementChange;
import org.javers.core.diff.changetype.container.ListChange;
import org.javers.core.metamodel.object.InstanceId;
import org.javers.core.metamodel.type.JaversType;
import org.javers.core.metamodel.type.ManagedType;
import org.javers.repository.jql.QueryBuilder;
import org.springframework.stereotype.Service;
import pl.ilpiu.clothingfactory.material.Material;
import pl.ilpiu.clothingfactory.product.Product;
import pl.ilpiu.clothingfactory.product.colour.Colour;
import pl.ilpiu.clothingfactory.product.size.Size;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class ProductChangesService {
    private final ProductChangesRepository productChangesRepository;

    private final Javers javers;

    ProductChangesService(final ProductChangesRepository productChangesRepository, final Javers javers) {
        this.productChangesRepository = productChangesRepository;
        this.javers = javers;
    }

    List<ProductChanges> getAllProductChangesByProductId(Long productId) {
        return productChangesRepository.findAllByProductId(productId);
    }

    public void archiveProductChanges(Product beforeUpdate, Product afterUpdate) {


        // TODO sprawdzic, czy w listach zagniezdzonych (kolor, material, rozmiar) nie potrzeba usunac adnotacji @Diffinclude
        // dzieki tej adnotacji sprawdzane sa tylko numery id kolorow w listach przed i po aktualizacji


        // utworzenie pustego kontenera na zmiany
        List<ProductChanges> changesToCommit = new ArrayList<>();

        System.out.println("beforeUpdate = " + beforeUpdate);
        System.out.println("afterUpdate = " + afterUpdate);

        // pobranie numeru id archwizowanego produktu
        Long productId = beforeUpdate.getId();

        // utworzenie listy zmian pomiędzy obiektami produktu
        Diff productsDiff = javers.compare(beforeUpdate, afterUpdate);


//        productsDiff.groupByObject().forEach(
//                System.out::println
//        );

//        productsDiff.getChangesByType(ListChange.class).forEach(listChange -> {


//            listChange.getChanges().forEach(
//                    test -> {
//                        test.
//                    }
//            );

//            System.out.println("listChange = " + listChange);
//        });


//        System.out.println("tu0");
//        productsDiff.getChanges().forEach(change -> {

//            change.


//            System.out.println(change);
//        });

//        System.out.println("tu1");
//        System.out.println(productsDiff.prettyPrint());
//
//        System.out.println("tu2");
//        System.out.println(javers.getJsonConverter().toJson(productsDiff));

//
//        System.out.println("tu");
//        productsDiff.groupByObject().forEach(byObject -> {
//            System.out.println("* changes on " +byObject.getGlobalId().value() + " : ");
//            byObject.get().forEach(change -> System.out.println("  - " + change));
//        });


        // utworzenie listy zmian pomiędzy obiektami materiału w produkcie
//        Diff materialsDiff = javers.compareCollections(beforeUpdate.getMaterials(), afterUpdate.getMaterials(), Material.class);

//        System.out.println("materialsDiff = " + materialsDiff);

        // utworzenie listy zmian pomiędzy listami kolorów w produkcie
//        Diff coloursDiff = javers.compareCollections(beforeUpdate.getColours(), afterUpdate.getColours(), Colour.class);

//        System.out.println("size: " + productsDiff.getChanges().size());
//
//        System.out.println(productsDiff.getChanges().devPrint());
//
//        System.out.println(productsDiff.getChanges().groupByObject().toString());
//
//        System.out.println(productsDiff.getChanges().get(1).toString());
//
//        System.out.println(productsDiff.getChanges().get(1).getAffectedGlobalId().value());
//
//        System.out.println(productsDiff.getChanges().get(1).getAffectedLocalId());

//        productsDiff.getChanges().get(1).

        // nieprzydatne
//        System.out.println(productsDiff.getChanges().get(1).getAffectedGlobalId().value().getClass());
//        System.out.println(productsDiff.getChanges().get(1).getAffectedGlobalId().value().getClass().getSimpleName());


        // utworzenie listy zmian pomiędzy obiektami rozmiaru w produkcie
//        Diff sizesDiff = javers.compareCollections(beforeUpdate.getSizes(), afterUpdate.getSizes(), Size.class);

//        System.out.println("sizesDiff = " + sizesDiff);

        // przekazanie list
        // iteracja po listach
        // zapsanie zmian do nowej listy
        // dodanie listy do changes do commit

//        List<ProductChanges> materialChanges = getNestedListChanges(beforeUpdate.getMaterials(), afterUpdate.getMaterials());


//        Changes listOfDifferences = productsDiff.getChanges();
//
//        // porównanie kolorów
//        javers.compareCollections(beforeUpdate.getColours(), afterUpdate.getColours(), Colour.class).getChangesByType(ValueChange.class).forEach(
//                change -> {
//                    System.out.println("change colour list = " + change);
//                }
//        );

//        System.out.println("tu");

//        javers.findChanges(QueryBuilder.byInstanceId(productId, Product.class).build()).forEach(
//                change -> {
//                    System.out.println("change = " + change);
//                }
//        );

//        System.out.println("tam");

        // VALUECHANGE.CLASS -> zmiana na zwykłych polach, nie wyłapuje list
        // dodanie wszystkich zmian w obiektach do listy
//        List<ListChange> test = productsDiff.getChangesByType(ListChange.class);
//
//        test.forEach(
//                change -> {
//
//                    change.getChanges().forEach(
//                            change1 -> {
//                                System.out.println(change1);
//                                System.out.println(change1.getIndex().toString());
//
//                            }
//                    );
//
//                    System.out.println("change = " + change);
//                }
//        );

//        productsDiff.getChanges().forEach(
//                change -> {
//                    System.out.println(change.getAffectedLocalId());
//                    System.out.println("change = " + change);
//                }
//        );

//        Change ch = productsDiff.getChanges().get(1);
//        System.out.println("ch = " + ch);
//        ListChange listChange = (ListChange) ch;
//
//        Object originalValue = listChange.getLeft();
//        Object newValue = listChange.getRight();
//
//        System.out.println("listChange = " + listChange);
//        System.out.println("originalValue = " + originalValue);
//        System.out.println("newValue = " + newValue);

        System.out.println("tutaj sprawdzenie dla listy");

        System.out.println("przed zmianą colour list: " + beforeUpdate.getColours());
        System.out.println("po zmianie colour list: " + afterUpdate.getColours());


        javers.compare(beforeUpdate, afterUpdate).getChanges().forEach(change -> {
            String changeType = change.getClass().getSimpleName();
            System.out.println(changeType);
            if (changeType.equals("ValueChange")) {
                ValueChange valueChange = (ValueChange) change;
                changesToCommit.add(new ProductChanges(
                        productId,
                        valueChange.getPropertyName(),
                        Optional.ofNullable(valueChange.getLeft()).map(Object::toString).orElse(StringUtils.EMPTY),
                        Optional.ofNullable(valueChange.getRight()).map(Object::toString).orElse(StringUtils.EMPTY),
                        "test"
                ));
                System.out.println("value change = " + change);
            }
            if (changeType.equals("ListChange")) {
                String propertyName = ((ListChange) change).getPropertyName();
                // propertyName = colours, sizes, materials
                // switch -> 3 casy,

                // private ProductChanges createProductChanges (String  ) {}
                // List <Material>
                // List <Size>
                // List <Colour>

                changesToCommit.add(getE(beforeUpdate, afterUpdate, productId, propertyName));

                System.out.println("list change = " + change);
            }
//            System.out.println(change.getClass().getSimpleName()); // ValueChange lub ListChange
            System.out.println("-------------------");
        });


//            productsDiff.getChanges().forEach(change -> {
//                System.out.println(change);

//            changesToCommit.add(new ProductChanges(
//                    productId,
//                    change.getPropertyName(),
//                    Optional.ofNullable(change.getLeft()).map(Object::toString).orElse(StringUtils.EMPTY),
//                    Optional.ofNullable(change.getRight()).map(Object::toString).orElse(StringUtils.EMPTY),
//                    "test"
//            ));
//        });

        // zapis listy zmian do bazy danych
        productChangesRepository.saveAll(changesToCommit);

    }


    // "colours" -> getColours()
    // "materials" -> getMaterials()
    // "sizes" -> getSizes()



    private static ProductChanges getE(final Product beforeUpdate, final Product afterUpdate, final Long productId, final String propertyName) {
        return new ProductChanges(
                productId,
                propertyName,
                beforeUpdate.getColours().toString(), // getColours, getMaterials, getSizes, [1, 2, 3]
                afterUpdate.getColours().toString(), //
                "test"
        );
    }

    // metoda



}