package pl.clothingfactory.knitting.KnittingDevices;

import lombok.Data;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

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
