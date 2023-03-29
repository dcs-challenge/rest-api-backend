package com.dcs.backend.repository;

import com.dcs.backend.entity.Weather;
import com.dcs.backend.entity.WeatherPk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WeatherRepository extends JpaRepository<Weather, WeatherPk> {
    List<Weather> findByCity(String city);
}
