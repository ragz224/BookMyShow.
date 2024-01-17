package com.bookmyshow.bookmyshow.Service;

import com.bookmyshow.bookmyshow.Exceptions.NoLongerSeatsAreAvailableException;
import com.bookmyshow.bookmyshow.Exceptions.NoSeatsFoundException;
import com.bookmyshow.bookmyshow.Exceptions.UserNotFoundException;
import com.bookmyshow.bookmyshow.Models.*;
import com.bookmyshow.bookmyshow.Repositories.*;
import com.bookmyshow.bookmyshow.Exceptions.ShowNotFoundException;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
@Service
public class BookingService {
    private ShowSeatRepository showSeatRepository;
    private ShowSeatTypeRepository ShowseatTypeepository;
    private ShowRepository showRepository;
    private UserRepository userRepository;
    private BookingRepository bookingRepository;
    private PricingService pricingService;

    @Autowired
    public BookingService(ShowSeatRepository showSeatRepository, ShowSeatTypeRepository showseatTypeepository,
                          ShowRepository showRepository, UserRepository userRepository,
                          BookingRepository bookingRepository, PricingService pricingService) {

        this.showSeatRepository = showSeatRepository;
        ShowseatTypeepository = showseatTypeepository;
        this.showRepository = showRepository;
        this.userRepository = userRepository;
        this.bookingRepository = bookingRepository;
        this.pricingService = pricingService;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)

    public Booking BookAMovie(Long ShowId, List<Long> showSeatId, Long userId) throws ShowNotFoundException,
           NoSeatsFoundException, NoLongerSeatsAreAvailableException, UserNotFoundException {

       /*----Start lock here for TODAY---------
        * 1. Get the user from the user id
        * 2. Get the show from the show id
        * ----------Take a lock----------------
        * 3. Get the show seats from the seat ids
        * 4. Check if the seats are available
        * 5. If yes, make the status as blocked (or booking in progress)
        * ----------Release the lock------------
        * 6. Save updated show seats in DB and end the lock
        * ------ END LOCK HERE FOR TODAY ---------
        * */

        Optional<Show> opsShow = showRepository.findById(ShowId);
        if(opsShow.isEmpty()) {
            throw new ShowNotFoundException();
        }
        Show SavedShow = opsShow.get();
        showRepository.save(SavedShow);


    Optional<User> OpsUser = userRepository.findById(userId);
    if(OpsUser.isEmpty()) {
        throw new UserNotFoundException();
    }

    User savedUser = OpsUser.get();
    userRepository.save(savedUser);

        List<ShowSeat> ListofShowSeats = showSeatRepository.findAllById(showSeatId);
        if(ListofShowSeats.isEmpty()) {
            throw new NoSeatsFoundException();
        }

        for(ShowSeat showSeat : ListofShowSeats) {

            if (!(showSeat.getShowSeatStatus().equals(ShowSeatStatus.AVAILABLE) ||
                    (showSeat.getShowSeatStatus().equals(ShowSeatStatus.BLOCKED) &&
                            Duration.between(showSeat.getDate_BlockedAt().toInstant(),
                                    new Date().toInstant()).toMinutes() > 15))) {
                throw new NoLongerSeatsAreAvailableException();
            }
        }
            List<ShowSeat> savedSeats = new ArrayList<>();
            for(ShowSeat showSeat1: ListofShowSeats) {
                showSeat1.setShowSeatStatus(ShowSeatStatus.BLOCKED);
                showSeat1.setDate_BlockedAt(new Date());
                savedSeats.add(showSeatRepository.save(showSeat1));
            }
            Booking booking = new Booking();
            booking.setUser(savedUser);
            booking.setBookingStatus(BookingStatus.PENDING);
            booking.setShow(SavedShow);
            booking.setShowSeat(savedSeats);
            booking.setAmount(pricingService.calculatedPrice(ListofShowSeats,SavedShow));
            Booking SavedBooking =  bookingRepository.save(booking);
            return SavedBooking;

    }

}
