package pl.clothingfactory.sewing;

import pl.clothingfactory.common.Status;

import java.util.Date;

public interface SewingScheduleBasicInfo {

    Long getId();

    Long getProductId();

    Long getMaterialId();
    String getProductName();

    String getSizeName();
    String getColourName();

    Integer getAmount();
    Integer getPriority();

    Status getStatus();

    Date getScheduledOn();

    String getSeamstressName();

    Long getSeamstressId();

    Long getSizeId();

    Long getColourId();

    String getMaterialName();

}
