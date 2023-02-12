package pl.clothingfactory.labels;


import lombok.Data;
import pl.clothingfactory.common.Schedule;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

@Entity
@Data
@Table(name = "labels_schedules")
//@AllArgsConstructor
//@NoArgsConstructor
public class LabelsSchedule extends Schedule {

    @Enumerated(EnumType.STRING)
    private LabelsType labelsType;

//    public LabelsSchedule(CuttingSchedule cuttingSchedule){
//        this.setAmount(cuttingSchedule.getAmount());
//        this.setColour(cuttingSchedule.getColour());
//        this.setPriority(cuttingSchedule.getPriority());
//        this.setProduct(cuttingSchedule.getProduct());
//        this.setStatus(cuttingSchedule.getStatus());
//        this.setSize(cuttingSchedule.getSize());
//        this.setScheduledOn(new Date());
//    }
}
