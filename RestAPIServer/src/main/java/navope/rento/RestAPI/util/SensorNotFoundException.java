package navope.rento.RestAPI.util;

public class SensorNotFoundException extends RuntimeException{
    public SensorNotFoundException(String msg){
        super(msg);
    }
}
