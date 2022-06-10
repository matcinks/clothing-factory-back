package pl.ilpiu.clothingfactory.product.size;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Entity
@Table(name = "sizes")
public class Size {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(unique = true)
    private String name;

    private Date createdAt;

    @PrePersist
    public void createdAt(){
        this.createdAt = new Date();
    }
}