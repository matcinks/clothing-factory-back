package pl.ilpiu.clothingfactory.labels;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.ilpiu.clothingfactory.common.Schedule;
import pl.ilpiu.clothingfactory.cutting.CuttingSchedule;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Data
@Table(name = "labels_schedules")
@AllArgsConstructor
@NoArgsConstructor
public class LabelsSchedule extends Schedule {

    @Enumerated(EnumType.STRING)
    private LabelsType labelsType;

    public LabelsSchedule(CuttingSchedule cuttingSchedule){
        this.setAmount(cuttingSchedule.getAmount());
        this.setColour(cuttingSchedule.getColour());
        this.setPriority(cuttingSchedule.getPriority());
        this.setProduct(cuttingSchedule.getProduct());
        this.setStatus(cuttingSchedule.getStatus());
        this.setSize(cuttingSchedule.getSize());
        this.setScheduledOn(new Date());
    }
}
