package com.dcs.backend.controller;

import com.dcs.backend.entity.Weather;
import com.dcs.backend.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Weather saveWeather(@RequestBody @Validated Weather weather){
        // Weather existingWeather = weatherService.findWeatherByCityAndDate()
        return weatherService.saveWeather(weather);
    }

    @GetMapping("")
    public List<Weather> findWeather(@RequestParam(required=false) String city, @RequestParam(required=false) String date){
        // return weatherService.findWeatherByCity(city);
        return weatherService.findWeatherByCityAndDate(city, date);
    }

    // private Weather verifyWeatherDoesNotExistForDate


}
