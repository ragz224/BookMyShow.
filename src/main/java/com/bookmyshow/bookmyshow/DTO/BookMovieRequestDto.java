package com.bookmyshow.bookmyshow.DTO;

import com.bookmyshow.bookmyshow.Models.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class BookMovieRequestDto {
    private List<Long> showseatId;
    private Long userId;
    private Long showId;


}
