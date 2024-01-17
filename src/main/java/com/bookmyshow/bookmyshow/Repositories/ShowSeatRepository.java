package com.bookmyshow.bookmyshow.Repositories;

//import com.bookmyshow.bookmyshow.Models.Show;
import com.bookmyshow.bookmyshow.Models.Show;
import com.bookmyshow.bookmyshow.Models.ShowSeat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShowSeatRepository extends JpaRepository<ShowSeat,Long> {
    @Override
    List<ShowSeat> findAllById(Iterable<Long> longs);

    public ShowSeat save(ShowSeat showSeat);
}
