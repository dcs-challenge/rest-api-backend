package com.dcs.backend.repository;

import com.dcs.backend.entity.Weather;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class WeatherRepositoryTest {

    @Autowired
    private WeatherRepository weatherRepository;

    @Autowired
    private TestEntityManager entityManager;

    private Weather weather_city1_date1;
    private Weather weather_city1_date2;
    private Weather weather_city2_date1;

    @BeforeEach
    void setUp() {
        weather_city1_date1 =
                Weather.builder().city("city1").date("2023-03-31").temperature("1").build();
        weather_city1_date2 =
                Weather.builder().city("city1").date("2023-04-01").temperature("2").build();
        weather_city2_date1 =
                Weather.builder().city("city2").date("2023-03-31").temperature("3").build();

        entityManager.persist(weather_city1_date1);
        entityManager.persist(weather_city1_date2);
        entityManager.persist(weather_city2_date1);
    }

    @Test
    public void weatherListIsReturnedOnFindingByCity(){
        List<Weather> foundWeatherList = weatherRepository.findByCity("city1");

        List<Weather> expectedWeatherList = new ArrayList<>();
        expectedWeatherList.add(weather_city1_date1);
        expectedWeatherList.add(weather_city1_date2);

        assertEquals(foundWeatherList, expectedWeatherList);

    }

    @Test
    public void weatherListIsReturnedOnFindingByDate(){
        List<Weather> foundWeatherList = weatherRepository.findByDate("2023-03-31");

        List<Weather> expectedWeatherList = new ArrayList<>();
        expectedWeatherList.add(weather_city1_date1);
        expectedWeatherList.add(weather_city2_date1);

        assertEquals(foundWeatherList, expectedWeatherList);

    }

    @Test
    public void weatherListIsReturnedOnFindingByCityAndDate(){
        List<Weather> foundWeatherList = weatherRepository.findByCityAndDate("city1","2023-03-31");

        List<Weather> expectedWeatherList = new ArrayList<>();
        expectedWeatherList.add(weather_city1_date1);

        assertEquals(foundWeatherList, expectedWeatherList);
    }

    @Test
    public void weatherListIsDeletedOnDeletingByCity(){
        weatherRepository.deleteByCity("city1");
        List<Weather> foundWeatherList = weatherRepository.findByCity("city1");
        List<Weather> expectedWeatherList = new ArrayList<>();
        assertEquals(foundWeatherList, expectedWeatherList);

    }

}