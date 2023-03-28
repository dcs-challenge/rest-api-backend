package com.dcs.backend.service;

import com.dcs.backend.entity.Weather;
import com.dcs.backend.repository.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeatherService {
    @Autowired
    private WeatherRepository weatherRepository;

    public Weather saveWeather(Weather weather) {
        return weatherRepository.save(weather);
    }
}
