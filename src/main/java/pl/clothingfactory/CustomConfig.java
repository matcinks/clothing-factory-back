package pl.clothingfactory;

import org.javers.core.Javers;
import org.javers.core.JaversBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.javers.core.diff.ListCompareAlgorithm.*;

@Configuration
class CustomConfig {

    @Bean
    Javers javersInstance () {
        return JaversBuilder.javers()
                .withListCompareAlgorithm(LEVENSHTEIN_DISTANCE)
//                .withListCompareAlgorithm(SIMPLE)
//                .withListCompareAlgorithm(AS_SET)
//                .withPackagesToScan("pl.ilpiu.clothingfactory")
                .build();
    }
}
