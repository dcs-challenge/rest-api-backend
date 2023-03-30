package com.dcs.backend.service;

import com.dcs.backend.entity.Weather;
import com.dcs.backend.exception.WeatherAlreadyExistsException;
import com.dcs.backend.repository.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class WeatherService {
    @Autowired
    private WeatherRepository weatherRepository;

    public Weather saveWeather(Weather weather) {

        List<Weather> existingWeather = weatherRepository.findByCityAndDate(weather.getCity(), weather.getDate());
        if (existingWeather.isEmpty()){
            return weatherRepository.save(weather);
        }
        else
            throw new WeatherAlreadyExistsException("Weather already exists");

    }

    public List<Weather> findWeatherByCity(String city) {
        return weatherRepository.findByCity(city);
    }

    public List<Weather> findWeatherByCityAndDate(String city, String date) {
        if (city == null)
            return weatherRepository.findByDate(date);
        else if (date == null)
            return weatherRepository.findByCity(city);
        else
            return weatherRepository.findByCityAndDate(city, date);

    }
}
