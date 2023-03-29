package com.dcs.backend.controller;

import com.dcs.backend.entity.Weather;
import com.dcs.backend.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @PostMapping("")
    public Weather saveWeather(@RequestBody Weather weather){
        return weatherService.saveWeather(weather);
    }

    @GetMapping("")
    public List<Weather> findWeather(@RequestParam(required=false) String city){
        return weatherService.findWeatherByCity(city);
    }

}
