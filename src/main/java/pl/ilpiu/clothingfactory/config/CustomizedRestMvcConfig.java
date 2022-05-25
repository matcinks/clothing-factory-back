package pl.ilpiu.clothingfactory.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import pl.ilpiu.clothingfactory.material.Composition;
import pl.ilpiu.clothingfactory.material.Material;

//TODO This class is overriding configuration properties for REST endpoints

@Configuration
public class CustomizedRestMvcConfig implements RepositoryRestConfigurer {

    // TODO this method is exposing id's numbers in Composition and Material classes
    @Override
    public void configureRepositoryRestConfiguration(final RepositoryRestConfiguration config, final CorsRegistry cors) {
        config.exposeIdsFor(Composition.class, Material.class);
    }
}
