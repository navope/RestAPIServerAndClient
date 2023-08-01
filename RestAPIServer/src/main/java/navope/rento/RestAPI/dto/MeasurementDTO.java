package navope.rento.RestAPI.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class MeasurementDTO {

    @Size(min = -100, max = 100, message = "Value must be between -100 and 100")
    private double value;

    @NotNull
    private Boolean raining;

    @NotNull
    private SensorDTO sensorDTO;

    public MeasurementDTO() {
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Boolean getRaining() {
        return raining;
    }

    public void setRaining(Boolean raining) {
        this.raining = raining;
    }

    public SensorDTO getSensorDTO() {
        return sensorDTO;
    }

    public void setSensorDTO(SensorDTO sensorDTO) {
        this.sensorDTO = sensorDTO;
    }
}
