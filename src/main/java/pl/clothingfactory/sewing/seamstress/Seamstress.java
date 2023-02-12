package pl.clothingfactory.sewing.seamstress;

import lombok.Data;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "seamstresses")
public
class Seamstress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;
}
