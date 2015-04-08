package model;

/**
 * Created by ajou on 2015-04-08.
 */
public class Environment {
    private String temperature;
    private String lightBrightness;
    private String humidity;

    public Environment() {
    }

    public Environment(String temperature, String lightBrightness, String humidity) {
        this.temperature = temperature;
        this.lightBrightness = lightBrightness;
        this.humidity = humidity;
    }

    public String getTemperature() {
        return temperature;
    }

    public String getLightBrightness() {
        return lightBrightness;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public void setLightBrightness(String lightBrightness) {
        this.lightBrightness = lightBrightness;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    @Override
    public String toString() {
        return "Environment{" +
                "temperature='" + temperature + '\'' +
                ", lightBrightness='" + lightBrightness + '\'' +
                ", humidity='" + humidity + '\'' +
                '}';
    }
}
