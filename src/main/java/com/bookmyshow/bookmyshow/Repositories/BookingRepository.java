package com.bookmyshow.bookmyshow.Repositories;

import com.bookmyshow.bookmyshow.Models.Booking;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    @Override
    Optional<Booking> findById(Long aLong);

    @Override
    Booking save(Booking booking);
}
