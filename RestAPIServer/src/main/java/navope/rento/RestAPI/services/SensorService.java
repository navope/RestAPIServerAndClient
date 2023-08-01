package navope.rento.RestAPI.services;

import navope.rento.RestAPI.models.Sensor;
import navope.rento.RestAPI.repositories.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.Optional;

@Service
public class SensorService {

    private final SensorRepository sensorRepository;

    @Autowired
    public SensorService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    @Transactional
    public void register(Sensor sensor) {
        sensor.setRegisteredAt(new Date());
        sensorRepository.save(sensor);
    }

    public Optional<Sensor> getSensor(String name) {
        return sensorRepository.findByName(name);
    }
}
