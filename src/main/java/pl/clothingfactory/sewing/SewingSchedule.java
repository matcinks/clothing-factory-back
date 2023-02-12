package pl.clothingfactory.sewing;

import lombok.Data;
import pl.clothingfactory.common.ProductionSchedule;
import pl.clothingfactory.sewing.seamstress.Seamstress;

import jakarta.persistence.*;


@Entity
@Data
@Table(name = "sewing_schedules")
public class SewingSchedule extends ProductionSchedule {

    @ManyToOne(targetEntity = Seamstress.class)
    @JoinColumn(name = "seamstress_id", referencedColumnName = "id")
    private Seamstress seamstress;

}
