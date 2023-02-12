package pl.clothingfactory.cutting;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import pl.clothingfactory.common.Schedule;

import jakarta.persistence.*;

// TODO zmienic dziedziczenie na ProductionSchedul, zeby uwzglednic material
@Entity
@Table(name = "cutting_schedules")
@Data
public class CuttingSchedule extends Schedule{

}