package com.dcs.backend.controller;

import com.dcs.backend.entity.Weather;
import com.dcs.backend.exception.ErrorResponse;
import com.dcs.backend.exception.ParamsMissingException;
import com.dcs.backend.exception.WeatherAlreadyExistsException;
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
        // List<Weather> existingWeather = weatherService.findWeatherByCityAndDate(weather.getCity(), weather.getDate()).orElse(null);
        return weatherService.saveWeather(weather);
    }

    @GetMapping("")
    public List<Weather> findWeather(@RequestParam(required=false) String city, @RequestParam(required=false) String date){
        verifyAtLeastOneParameterIsPresent(city, date);
        return weatherService.findWeatherByCityAndDate(city, date);
    }

    @GetMapping("/simulate500")
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public void simulate500(){

    }

    private void verifyAtLeastOneParameterIsPresent(String city, String date) {
        if (city == null && date == null){
            throw new ParamsMissingException("City and date params are missing");
        }
    }

    @ExceptionHandler(WeatherAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleWeatherAlreadyExistsException(WeatherAlreadyExistsException ex){
        return new ErrorResponse((HttpStatus.CONFLICT.value()), ex.getMessage());
    }

    @ExceptionHandler(ParamsMissingException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ErrorResponse handleParamsMissingException(ParamsMissingException ex){
        return new ErrorResponse((HttpStatus.UNPROCESSABLE_ENTITY.value()), ex.getMessage());
    }


}
