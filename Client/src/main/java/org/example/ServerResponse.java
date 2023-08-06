package org.example;

import java.util.List;

public class ServerResponse {
    List<MeasurementDTO> measurementDTOS;

    public ServerResponse(List<MeasurementDTO> measurementDTOS) {
        this.measurementDTOS = measurementDTOS;
    }

    public List<MeasurementDTO> getMeasurementDTOS() {
        return measurementDTOS;
    }

    public void setMeasurementDTOS(List<MeasurementDTO> measurementDTOS) {
        this.measurementDTOS = measurementDTOS;
    }
}
