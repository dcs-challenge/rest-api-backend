package com.dcs.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Weather {

    /*
    TODO : Change the entity and id so that id is unique.
    This is a half cooked entity to get going.
     */
    @Id
    private String cityId;
    private String cityName;
    private Date date;
    private String weatherStatus;
}
