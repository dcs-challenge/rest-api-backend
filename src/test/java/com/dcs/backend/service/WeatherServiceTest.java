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

    private List<Weather> weatherListCity1;
    private Weather weather_city1_date1;
    private Weather weather_city1_date2;

    @BeforeEach
    void setUp() {
        weatherListCity1 = new ArrayList<>();
        List<Weather> weatherListDate1 = new ArrayList<>();
        weather_city1_date1 =
                Weather.builder().city("city1").date("2023-03-31").temperature("1").build();
        weather_city1_date2 =
                Weather.builder().city("city2").date("2023-04-01").temperature("2").build();
        weatherListCity1.add(weather_city1_date1);
        weatherListCity1.add(weather_city1_date2);
        Mockito.when(weatherRepository.findByCity("city1")).thenReturn(weatherListCity1);
    }


    @Test
    public void weather_should_be_returned_for_valid_city(){
        // String city = "city1";
        List<Weather> expectedWeatherList = new ArrayList<>();
        expectedWeatherList.add(weather_city1_date1);
        expectedWeatherList.add(weather_city1_date2);

        List<Weather> foundWeatherList = weatherService.findWeatherByCity("city1");
        assertEquals(foundWeatherList, expectedWeatherList);
        /*
        for (Weather weather : found){
            System.out.println("Asserting " + weather.getCity() + "and " + city);
            assertEquals(weather.getCity(), weather_city1_date1
        }

         */

    }

}