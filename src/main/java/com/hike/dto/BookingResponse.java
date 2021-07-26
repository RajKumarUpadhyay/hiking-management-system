package com.hike.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hike.entity.Booking;
import com.hike.entity.Trail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingResponse {
    @NonNull
    Booking booking;
    @NonNull
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    Trail trail;
}
