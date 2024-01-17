package com.bookmyshow.bookmyshow.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
@Getter
@Setter
@Entity
public class Booking extends  BaseModel{
    @Enumerated(EnumType.ORDINAL)
    private BookingStatus bookingStatus;
    @ManyToOne
    private User user;

    @ManyToMany
    private List<ShowSeat> showSeat;

    @ManyToOne
    private Show show;
    private int amount;

//    @ManyToOne
//    private Movie movie;
//
//    @ManyToOne
//    private Theatre theatre;

    @OneToMany
    private  List<Payment> payment;

    private Date bookedAt;

}
