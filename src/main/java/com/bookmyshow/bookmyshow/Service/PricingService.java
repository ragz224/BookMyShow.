package com.bookmyshow.bookmyshow.Service;

import com.bookmyshow.bookmyshow.Models.Show;
import com.bookmyshow.bookmyshow.Models.ShowSeat;
import com.bookmyshow.bookmyshow.Models.ShowSeatType;
import com.bookmyshow.bookmyshow.Repositories.ShowSeatTypeRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Getter
@Setter
@Service
public class PricingService {
    private ShowSeatTypeRepository showSeatTypeRepository;

    @Autowired
    public PricingService(ShowSeatTypeRepository showSeatTypeRepository) {
        this.showSeatTypeRepository = showSeatTypeRepository;
    }

    public int calculatedPrice(List<ShowSeat> showSeats , Show show) {
        List<ShowSeatType> showSeatTypeList = showSeatTypeRepository.findAllByShow(show);
        int amount = 0;

        for(ShowSeat showSeat: showSeats) {
            for(ShowSeatType showSeatType: showSeatTypeList) {
                if(showSeat.getSeat().getSeatType().equals(showSeatType.getSeatType())) {
                    amount+=showSeatType.getPrice();
                }
            }
        }

        return amount;
    }


}

