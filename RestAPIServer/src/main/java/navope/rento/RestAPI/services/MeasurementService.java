package navope.rento.RestAPI.services;

import navope.rento.RestAPI.models.Measurement;
import navope.rento.RestAPI.repositories.MeasurementRepository;
import navope.rento.RestAPI.util.SensorNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
public class MeasurementService {

    private final SensorService sensorService;
    private final MeasurementRepository measurementRepository;

    @Autowired
    public MeasurementService(SensorService sensorService,
                              MeasurementRepository measurementRepository) {
        this.sensorService = sensorService;
        this.measurementRepository = measurementRepository;
    }

    @Transactional
    public void add(Measurement measurement) throws SensorNotFoundException {
        String sensorName = measurement.getSensor().getName();
        if (sensorService.getSensor(sensorName).isEmpty()){
            throw new SensorNotFoundException("Such a sensor not a found!");
        }
        measurement.setSensor(sensorService.getSensor(sensorName).get());
        measurement.setAddedAt(new Date());
        measurementRepository.save(measurement);
    }

    public List<Measurement> getMeasurements() {
        return measurementRepository.findAll();
    }

    public long getNumberOfRainyDays(){
        return measurementRepository.countMeasurementByRaining(true);
    }
}
