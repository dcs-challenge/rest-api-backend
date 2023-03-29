package com.dcs.backend.service;

import com.dcs.backend.entity.Weather;
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
        return weatherRepository.save(weather);
    }

    public List<Weather> findWeatherByCity(String city) {
        return weatherRepository.findByCity(city);
    }

    public List<Weather> findWeatherByCityAndDate(String city, String date) {
        return weatherRepository.findByCityAndDate(city, date);
    }
}
