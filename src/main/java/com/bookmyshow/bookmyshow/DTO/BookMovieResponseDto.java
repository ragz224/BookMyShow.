package com.bookmyshow.bookmyshow.DTO;

import com.bookmyshow.bookmyshow.Models.Booking;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookMovieResponseDto {
    private Long bookingId;
    private ResponseStatus responseStatus;
    private int amount;
}
