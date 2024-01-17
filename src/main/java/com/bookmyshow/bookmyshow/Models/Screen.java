package com.bookmyshow.bookmyshow.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@Entity
public class  Screen extends  BaseModel{

    private String name;

    @OneToMany
    private List<Seat> seat;
//    private List<Show> show;
    @Enumerated(EnumType.ORDINAL)
    private List<Features> features;

}
