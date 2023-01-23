package pl.ilpiu.clothingfactory.sewing;

import lombok.Data;
import pl.ilpiu.clothingfactory.common.ProductionSchedule;
import pl.ilpiu.clothingfactory.common.Schedule;
import pl.ilpiu.clothingfactory.sewing.seamstress.Seamstress;

import javax.persistence.*;


@Entity
@Data
@Table(name = "sewing_schedules")
public class SewingSchedule extends ProductionSchedule {

    @ManyToOne(targetEntity = Seamstress.class)
    @JoinColumn(name = "seamstress_id", referencedColumnName = "id")
    private Seamstress seamstress;

}
