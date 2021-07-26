package com.hike.dto;

import com.hike.entity.Booking;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BookingRequest {
    @NonNull
    private Booking booking;
}
