package com.dcs.backend.service;

import com.dcs.backend.entity.Weather;
import com.dcs.backend.repository.WeatherRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class WeatherServiceTest {

    @Autowired
    private WeatherService weatherService;
    @MockBean
    private WeatherRepository weatherRepository;
    private Weather weather_city1_date1;
    private Weather weather_city1_date2;
    private Weather weather_city2_date1;

    private static final String city1 = "city1";
    private static final String city2 = "city2";
    private static final String city3 = "city3";
    private static final String date1 = "2023-03-31";
    private static final String date2 = "2023-04-01";
    private static final String date3 = "2023-04-02";

    @BeforeEach
    void setUp() {
        List<Weather>  weatherListCity1 = new ArrayList<>();
        List<Weather> weatherListDate1 = new ArrayList<>();
        List<Weather> weatherListCity1Date1 = new ArrayList<>();
        List<Weather> emptyWeatherList = new ArrayList<>();

        weather_city1_date1 =
                Weather.builder().city(city1).date(date1).temperature("1").build();
        weather_city1_date2 =
                Weather.builder().city(city1).date(date2).temperature("2").build();
        weather_city2_date1 =
                Weather.builder().city(city2).date(date1).temperature("2").build();

        weatherListCity1.add(weather_city1_date1);
        weatherListCity1.add(weather_city1_date2);

        weatherListDate1.add(weather_city1_date1);
        weatherListDate1.add(weather_city2_date1);

        weatherListCity1Date1.add(weather_city1_date1);

        Mockito.when(weatherRepository.findByCity(city1)).thenReturn(weatherListCity1);
        Mockito.when(weatherRepository.findByDate(date1)).thenReturn(weatherListDate1);
        Mockito.when(weatherRepository.findByCityAndDate(city1, date1)).thenReturn(weatherListCity1Date1);

        Mockito.when(weatherRepository.findByCity(city3)).thenReturn(emptyWeatherList);
        Mockito.when(weatherRepository.findByDate(date3)).thenReturn(emptyWeatherList);
        Mockito.when(weatherRepository.findByCityAndDate(city3, date3)).thenReturn(emptyWeatherList);

    }


    @Test
    public void weatherShouldBeReturnedForValidCity(){
        List<Weather> expectedWeatherList = new ArrayList<>();
        List<Weather> foundWeatherList = weatherService.findWeatherByCityAndDate(city1, null);

        expectedWeatherList.add(weather_city1_date1);
        expectedWeatherList.add(weather_city1_date2);

        assertEquals(expectedWeatherList, foundWeatherList);
    }

    @Test
    public void weatherShouldBeReturnedForValidDate(){
        List<Weather> expectedWeatherList = new ArrayList<>();
        List<Weather> foundWeatherList = weatherService.findWeatherByCityAndDate(null, date1);

        expectedWeatherList.add(weather_city1_date1);
        expectedWeatherList.add(weather_city2_date1);

        assertEquals(expectedWeatherList, foundWeatherList);
    }

    @Test
    public void weatherShouldBeReturnedForValidCityAndDate(){
        List<Weather> expectedWeatherList = new ArrayList<>();
        List<Weather> foundWeatherList = weatherService.findWeatherByCityAndDate(city1, date1);

        expectedWeatherList.add(weather_city1_date1);

        assertEquals(expectedWeatherList, foundWeatherList);
    }

    @Test
    public void emptyWeatherListShouldBeReturnedForNoCityEntry(){
        List<Weather> expectedWeatherList = new ArrayList<>();
        List<Weather> foundWeatherList = weatherService.findWeatherByCity(city3);

        assertEquals(expectedWeatherList, foundWeatherList);
    }

    @Test
    public void emptyWeatherListShouldBeReturnedForNoDateEntry(){
        List<Weather> expectedWeatherList = new ArrayList<>();
        List<Weather> foundWeatherList = weatherService.findWeatherByCity(date3);

        assertEquals(expectedWeatherList, foundWeatherList);
    }

    @Test
    public void emptyWeatherListShouldBeReturnedForNoCityAndDateEntry(){
        List<Weather> expectedWeatherList = new ArrayList<>();
        List<Weather> foundWeatherList = weatherService.findWeatherByCityAndDate(city3, date3);

        assertEquals(expectedWeatherList, foundWeatherList);
    }

}