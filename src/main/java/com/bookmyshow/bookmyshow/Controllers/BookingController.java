package com.bookmyshow.bookmyshow.Controllers;

import com.bookmyshow.bookmyshow.DTO.BookMovieRequestDto;
import com.bookmyshow.bookmyshow.DTO.BookMovieResponseDto;
import com.bookmyshow.bookmyshow.DTO.ResponseStatus;
import com.bookmyshow.bookmyshow.Exceptions.NoLongerSeatsAreAvailableException;
import com.bookmyshow.bookmyshow.Exceptions.NoSeatsFoundException;
import com.bookmyshow.bookmyshow.Exceptions.ShowNotFoundException;
import com.bookmyshow.bookmyshow.Exceptions.UserNotFoundException;
import com.bookmyshow.bookmyshow.Models.Booking;
import com.bookmyshow.bookmyshow.Service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller

public class BookingController {
    private BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    public BookMovieResponseDto bookmovie(BookMovieRequestDto bookMovieRequestDto ) throws UserNotFoundException,
           ShowNotFoundException, NoLongerSeatsAreAvailableException, NoSeatsFoundException {

        BookMovieResponseDto bookMovieResponseDto = new BookMovieResponseDto();
        Booking booking;
        try {
            booking = bookingService.BookAMovie(bookMovieRequestDto.getShowId(),
                    bookMovieRequestDto.getShowseatId(), bookMovieRequestDto.getUserId());

            bookMovieResponseDto.setResponseStatus(ResponseStatus.SUCESS);
            bookMovieResponseDto.setBookingId((long) booking.getId());
            bookMovieResponseDto.setAmount(booking.getAmount());


        }
        catch(Exception exe){
            bookMovieResponseDto.setResponseStatus(ResponseStatus.FAILURE);
       }


       return bookMovieResponseDto;
   }
}
