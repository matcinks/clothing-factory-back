package pl.clothingfactory.common;

import lombok.Data;
import pl.clothingfactory.material.Material;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Data
public class ProductionSchedule extends Schedule{

    @ManyToOne(targetEntity = Material.class)
    @JoinColumn(referencedColumnName = "id", name = "material_id", updatable = false)
    private Material material;

}
