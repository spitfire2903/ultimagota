package br.com.ricardonm.gotadagua.fragment;

import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.survivingwithandroid.weather.lib.WeatherClient;
import com.survivingwithandroid.weather.lib.WeatherConfig;
import com.survivingwithandroid.weather.lib.client.okhttp.WeatherDefaultClient;
import com.survivingwithandroid.weather.lib.exception.WeatherLibException;
import com.survivingwithandroid.weather.lib.model.CurrentWeather;
import com.survivingwithandroid.weather.lib.model.Location;
import com.survivingwithandroid.weather.lib.model.Weather;
import com.survivingwithandroid.weather.lib.provider.openweathermap.OpenweathermapProviderType;
import com.survivingwithandroid.weather.lib.request.WeatherRequest;

import java.text.DateFormat;
import java.util.Date;

import br.com.ricardonm.gotadagua.BaseFragment;
import br.com.ricardonm.gotadagua.R;
import br.com.ricardonm.gotadagua.auth.AuthKeys;

/**
 * Created by ricardomiranda.
 */
public class WeatherFragment extends BaseFragment {

    private WeatherConfig config;
    private WeatherClient client;
    private WeatherClient.ClientBuilder builder;

    private TextView txtCityField;
    private TextView txtUpdatedField;
    private TextView txtDetailsField;
    private TextView txtCurrentTemperatureField;
    private TextView txtWeatherIcon;
    private TextView txtMaxTemp;
    private TextView txtMinTemp;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = null;
        Typeface weatherFont = null;

        rootView = inflater.inflate(R.layout.fragment_weather, container, false);

        // Set the font for the weather forecast
        weatherFont = Typeface.createFromAsset(getActivity().getAssets(), "fonts/weathericons-regular-webfont.ttf");

        // Identifier the fields
        txtCityField = (TextView)rootView.findViewById(R.id.city_field);
        txtUpdatedField = (TextView)rootView.findViewById(R.id.updated_field);
        txtDetailsField = (TextView)rootView.findViewById(R.id.details_field);
        txtCurrentTemperatureField = (TextView)rootView.findViewById(R.id.current_temperature_field);
        txtWeatherIcon = (TextView)rootView.findViewById(R.id.weather_icon);
        txtMaxTemp = (TextView)rootView.findViewById(R.id.txtMaxTemp);
        txtMinTemp = (TextView)rootView.findViewById(R.id.txtMinTemp);

        txtWeatherIcon.setTypeface(weatherFont);

        try {
            // Create a new config for the weather api.
            config = new WeatherConfig();
            config.ApiKey = AuthKeys.OPEN_WEATHER_MAP;

            builder = new WeatherClient.ClientBuilder();

            client = builder.attach(this.getParentActivity())
                    .httpClient(WeatherDefaultClient.class)
                    .provider(new OpenweathermapProviderType())
                    .config(this.config)
                    .build();

            // Make a request for the weather api
            client.getCurrentCondition(new WeatherRequest(this.getParentActivity()
                    .getUserLocation().getLongitude(), this.getParentActivity()
                    .getUserLocation().getLatitude()), new WeatherClient.WeatherEventListener() {

                /**
                 * Callback for weather information
                 * @param currentWeather
                 */
                @Override
                public void onWeatherRetrieved(CurrentWeather currentWeather) {
                    Location location = null;
                    Weather.Condition condition = null;
                    Weather.Temperature temperature = null;
                    Weather.Wind wind = null;
                    Weather.Rain[] rains = null;
                    Weather.Clouds clouds = null;
                    DateFormat df = null;

                    df = DateFormat.getDateTimeInstance();

                    temperature = currentWeather.weather.temperature;
                    condition = currentWeather.weather.currentCondition;
                    location = currentWeather.weather.location;
                    wind = currentWeather.weather.wind;
                    rains = currentWeather.weather.rain;
                    clouds = currentWeather.weather.clouds;

                    txtCityField.setText(location.getCity()+", "+location.getCountry());
                    txtDetailsField.setText(condition.getWeatherCode().getLabel(getParentActivity()));
                    txtCurrentTemperatureField.setText(temperature.getTemp()+"");
                    txtUpdatedField.setText(df.format(new Date()));

                    txtMaxTemp.setText(temperature.getMaxTemp()+"");
                    txtMinTemp.setText(temperature.getMinTemp()+"");

                    setWeatherIcon(currentWeather.weather.currentCondition.getWeatherId(),
                            currentWeather.weather.location.getSunrise() * 1000, currentWeather.weather
                                    .location.getSunset()*1000);
                }

                @Override
                public void onWeatherError(WeatherLibException e) {
                    Log.d("WL", "Weather Error - parsing data");
                    e.printStackTrace();
                }

                @Override
                public void onConnectionError(Throwable throwable) {
                    Log.d("WL", "Connection error");
                    throwable.printStackTrace();
                }
            });
        }
        catch (Throwable t) {
            t.printStackTrace();
        }

        return rootView;
    }

    /**
     * Sets weather icon through the weather code
     * @param actualId
     * @param sunrise
     * @param sunset
     */
    private void setWeatherIcon(int actualId, long sunrise, long sunset){
        int id = actualId / 100;
        String icon = "";
        if(actualId == 800){
            long currentTime = new Date().getTime();
            if(currentTime>=sunrise && currentTime<sunset) {
                icon = getActivity().getString(R.string.weather_sunny);
            } else {
                icon = getActivity().getString(R.string.weather_clear_night);
            }
        } else {
            switch(id) {
                case 2 : icon = getActivity().getString(R.string.weather_thunder);
                    break;
                case 3 : icon = getActivity().getString(R.string.weather_drizzle);
                    break;
                case 7 : icon = getActivity().getString(R.string.weather_foggy);
                    break;
                case 8 : icon = getActivity().getString(R.string.weather_cloudy);
                    break;
                case 6 : icon = getActivity().getString(R.string.weather_snowy);
                    break;
                case 5 : icon = getActivity().getString(R.string.weather_rainy);
                    break;
            }
        }

        txtWeatherIcon.setText(icon);
    }
}
