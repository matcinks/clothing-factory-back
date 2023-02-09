package pl.clothingfactory.knitting.KnittingDevices;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity
@Table(name = "knitting_devices")
public class KnittingDevice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String gauge; // uiglenie

    @Min(value = 1, message = "Maszyna dziewiarska musi skladac sie z conajmniej jednej glowicy")
    private Integer headsAmount; // ilosc glowic
}
