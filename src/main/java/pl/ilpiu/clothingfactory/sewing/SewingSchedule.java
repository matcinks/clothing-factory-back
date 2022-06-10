package pl.ilpiu.clothingfactory.sewing;

import pl.ilpiu.clothingfactory.common.Schedule;
import pl.ilpiu.clothingfactory.sewing.seamstress.Seamstress;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "sewing_schedules")
class SewingSchedule extends Schedule {

    @ManyToOne
    private Seamstress seamstress;

}
