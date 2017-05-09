package program.weather;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;



import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    String baseUrl = "http://api.openweathermap.org/data/2.5/";
    String resUrl  = "http://openweathermap.org/img/w/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        WeatherService service = retrofit.create(WeatherService.class);

        service.getWeather(524901, "38359dce3962f66a606a8ba5164a3ac2", "metric").enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                if(response != null && response.code() == 200){
                    WeatherResponse weatherResponse = response.body();
                    WeatherMain     weatherMain     = weatherResponse.getWeatherMain();

                    String cityName = weatherResponse.getName();
                    String temp     = String.valueOf(weatherMain.getTemp());
                    String pressure = String.valueOf(weatherMain.getPressure());
                    String humidity = String.valueOf(weatherMain.getHumidity());
                    String iconUrl  = resUrl+weatherResponse.getWeather().get(0).getIconUrl()+".png";

                    ((TextView) findViewById(R.id.city)).setText(cityName);
                    ((TextView) findViewById(R.id.temp)).setText(temp);
                    ((TextView) findViewById(R.id.pressure)).setText(pressure);
                    ((TextView) findViewById(R.id.humidity)).setText(humidity);

                }else{
                    if(response == null){
                        Toast.makeText(MainActivity.this, "Response is null", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    Toast.makeText(MainActivity.this, "Error occurred, error code is "+response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Network errors occurred", Toast.LENGTH_SHORT).show();
            }
        });
    }
}