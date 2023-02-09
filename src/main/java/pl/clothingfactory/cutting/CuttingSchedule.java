package pl.clothingfactory.cutting;

import lombok.Data;
import pl.clothingfactory.common.Schedule;

import javax.persistence.*;

// TODO zmienic dziedziczenie na ProductionSchedul, zeby uwzglednic material
@Entity
@Table(name = "cutting_schedules")
@Data
public
class CuttingSchedule extends Schedule{

}