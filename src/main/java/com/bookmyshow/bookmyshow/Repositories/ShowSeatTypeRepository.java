package com.bookmyshow.bookmyshow.Repositories;

import com.bookmyshow.bookmyshow.Models.Seat;
import com.bookmyshow.bookmyshow.Models.Show;
import com.bookmyshow.bookmyshow.Models.ShowSeatType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShowSeatTypeRepository extends JpaRepository<ShowSeatType, Long> {

    List<ShowSeatType> findAllByShow(Show show);
}
