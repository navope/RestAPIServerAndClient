package navope.rento.RestAPI.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SensorDTO {

    @NotNull
    @Size(min = 3, max = 30, message = "Sensor name length must be between 3 and 30")
    private String name;

    public SensorDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
