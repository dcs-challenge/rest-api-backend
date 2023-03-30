package com.dcs.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@IdClass(WeatherPk.class)
public class Weather {
    @Id
    @NotEmpty
    private String city;
    @Id
    @NotEmpty
    private String date;

    @Column
    @NotEmpty
    private String temperature;
}
