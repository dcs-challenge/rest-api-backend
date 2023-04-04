package com.dcs.backend.controller;

import com.dcs.backend.entity.Weather;
import com.dcs.backend.service.UserService;
import com.dcs.backend.service.WeatherService;
import com.dcs.backend.utility.JWTUtility;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(WeatherController.class)
class WeatherControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private WeatherService weatherService;
    @MockBean
    private JWTUtility jwtUtility;
    @MockBean
    private UserService userService;
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
    }

    @Test
    void saveWeather() throws Exception {
        Weather inputWeather = weather_city1_date1;
        Mockito.when(weatherService.saveWeather(inputWeather)).thenReturn(weather_city1_date1);

        mockMvc.perform(MockMvcRequestBuilders.post("/weather")
                        .with(user("admin").password("password").roles("USER","ADMIN"))
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"city\":\"city1\",\"date\":\"2023-03-31\",\"temperature\":\"1\"}"))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    void findWeatherByCity() throws Exception {
        List<Weather> expectedWeatherList = new ArrayList<>();
        expectedWeatherList.add(weather_city1_date1);
        expectedWeatherList.add(weather_city1_date2);
        Mockito.when(weatherService.findWeatherByCityAndDate("city1", null)).thenReturn(expectedWeatherList);

        mockMvc.perform(MockMvcRequestBuilders.get("/weather")
                        .with(user("admin").password("password").roles("USER","ADMIN"))
                .contentType(MediaType.APPLICATION_JSON)
                .param("city", "city1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.[0].city").value("city1"))
                .andExpect(jsonPath("$.[1].city").value("city1"));

    }

    @Test
    void findWeatherByDate() throws Exception {
        List<Weather> expectedWeatherList = new ArrayList<>();
        expectedWeatherList.add(weather_city1_date1);
        expectedWeatherList.add(weather_city2_date1);
        Mockito.when(weatherService.findWeatherByCityAndDate(null, "2023-03-31")).thenReturn(expectedWeatherList);

        mockMvc.perform(MockMvcRequestBuilders.get("/weather")
                        .with(user("admin").password("password").roles("USER","ADMIN"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("date", "2023-03-31"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.[0].date").value("2023-03-31"))
                .andExpect(jsonPath("$.[1].date").value("2023-03-31"));

    }
}