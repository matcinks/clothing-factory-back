package pl.ilpiu.clothingfactory.knitting;

import pl.ilpiu.clothingfactory.common.Schedule;
import pl.ilpiu.clothingfactory.knitting.KnittingDevices.KnittingDevice;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.ManyToOne;

@Entity
@Table(name = "knitting_schedules")
class KnittingSchedule extends Schedule {

    @ManyToOne
    private KnittingDevice knittingDevice;

}
