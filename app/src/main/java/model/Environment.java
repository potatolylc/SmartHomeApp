package model;

/**
 * Created by ajou on 2015-04-08.
 */
public class Environment {
    private double temperature;
    private double lightBrightness;
    private double humidity;

    public Environment() {
    }

    public Environment(double temperature, double lightBrightness, double humidity) {
        this.temperature = temperature;
        this.lightBrightness = lightBrightness;
        this.humidity = humidity;
    }

    public double getTemperature() {
        return temperature;
    }

    public double getLightBrightness() {
        return lightBrightness;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public void setLightBrightness(double lightBrightness) {
        this.lightBrightness = lightBrightness;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    @Override
    public String toString() {
        return "Environment{" +
                "temperature=" + temperature +
                ", lightBrightness=" + lightBrightness +
                ", humidity=" + humidity +
                '}';
    }
}
