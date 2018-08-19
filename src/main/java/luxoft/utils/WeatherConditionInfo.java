package luxoft.utils;

import java.util.Objects;

public class WeatherConditionInfo {

    private int currentTemp;
    private String weatherCondition;
    private int minTemp;
    private int maxTemp;
    private int windSpeed;
    private int humidity;
    private double pressure;
    private double visibility;

    public int getCurrentTemp() {
        return currentTemp;
    }

    public void setCurrentTemp(int currentTemp) {
        this.currentTemp = currentTemp;
    }

    public String getWeatherCondition() {
        return weatherCondition;
    }

    public void setWeatherCondition(String weatherCondition) {
        this.weatherCondition = weatherCondition;
    }

    public int getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(int minTemp) {
        this.minTemp = minTemp;
    }

    public int getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(int maxTemp) {
        this.maxTemp = maxTemp;
    }

    public int getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(int windSpeed) {
        this.windSpeed = windSpeed;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public double getVisibility() {
        return visibility;
    }

    public void setVisibility(double visibility) {
        this.visibility = visibility;
    }

    @Override
    public String toString() {
        return "WeatherConditionInfo{" +
                "currentTemp=" + currentTemp +
                ", weatherCondition='" + weatherCondition + '\'' +
                ", minTemp=" + minTemp +
                ", maxTemp=" + maxTemp +
                ", windSpeed=" + windSpeed +
                ", humidity=" + humidity +
                ", pressure=" + pressure +
                ", visibility=" + visibility +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WeatherConditionInfo that = (WeatherConditionInfo) o;
        return currentTemp == that.currentTemp &&
                minTemp == that.minTemp &&
                maxTemp == that.maxTemp &&
                windSpeed == that.windSpeed &&
                humidity == that.humidity &&
                Double.compare(that.pressure, pressure) == 0 &&
                Double.compare(that.visibility, visibility) == 0 &&
                Objects.equals(weatherCondition, that.weatherCondition);
    }

}