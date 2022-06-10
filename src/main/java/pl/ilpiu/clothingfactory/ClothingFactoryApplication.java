package pl.ilpiu.clothingfactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.SpringVersion;

@SpringBootApplication
public class ClothingFactoryApplication {

    public static void main(String[] args) {
        // TODO REMOVE LATER
//        System.out.println("Spring Version: " + SpringVersion.getVersion());

        SpringApplication.run(ClothingFactoryApplication.class, args);
    }

}
