package com.dcs.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@IdClass(WeatherPk.class)
public class Weather {
    @Id
    private String city;
    @Id
    private String date;

    @Column
    private String temperature;
}
