package com.dcs.backend.controller;

import com.dcs.backend.entity.Weather;
import com.dcs.backend.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @PostMapping("")
    public Weather saveWeather(@RequestBody Weather weather){
        return weatherService.saveWeather(weather);
    }

}
