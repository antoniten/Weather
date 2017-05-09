package program.weather;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WeatherResponse {
    @SerializedName("name")
    @Expose
    String name;

    @SerializedName("main")
    @Expose
    WeatherMain weatherMain;

    @SerializedName("weather")
    List<WeatherInfo> weather = null;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setWeatherMain(WeatherMain weatherMain) {
        this.weatherMain = weatherMain;
    }

    public WeatherMain getWeatherMain() {
        return weatherMain;
    }

    public void setWeather(List<WeatherInfo> weather) {
        this.weather = weather;
    }

    public List<WeatherInfo> getWeather() {
        return weather;
    }
}
