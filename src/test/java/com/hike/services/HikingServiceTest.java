package com.hike.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.hike.dto.BookingRequest;
import com.hike.entity.Booking;
import com.hike.entity.HikerInfo;
import com.hike.exception.NoSuchElementFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class HikingServiceTest {

    @Autowired
    HikingService hikingService;

    BookingRequest bookingRequest = new BookingRequest();
    Booking booking = new Booking();
    HikerInfo info  = new HikerInfo();
    List<HikerInfo> hikersInfo = new ArrayList<>();

    ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
    String tempBookingRequest;

    @BeforeEach
    void setUp() throws JsonProcessingException {
        booking.setTrail_id(0);
        booking.setBookingDateAndTime(LocalDateTime.now().plusDays(1));

        info.setAge(30);
        info.setFirstName("Tom");
        hikersInfo.add(info);
        booking.setHikersInfo(hikersInfo);

        bookingRequest.setBooking(booking);
        tempBookingRequest = ow.writeValueAsString(bookingRequest);
    }

    @Test
    void getTrails() {
        assertNotNull(hikingService.getTrails());
        assertEquals(hikingService.getTrails().size(), 3);
    }

    @Test
    void getTrailById() {
        Throwable exception = assertThrows(NoSuchElementFoundException.class, () -> hikingService.getTrailById(5));
        assertEquals("No item found for trail id : 5", exception.getMessage());
        assertEquals(hikingService.getTrailById(1).getTrail_Id(), 1);
    }

    @Test
    void getBookingById() {
        Throwable exception = assertThrows(NoSuchElementFoundException.class, () -> hikingService.getBookingById(5));
        assertEquals("No reservation found for booking id : 5", exception.getMessage());
    }

    @Test
    void createBooking() {
        Throwable exception = assertThrows(NoSuchElementFoundException.class, () -> hikingService.createBooking(bookingRequest));
        assertEquals("Invalid Booking Request. Provided trail doesn't exists!", exception.getMessage());

        bookingRequest.getBooking().setTrail_id(1);
        bookingRequest.getBooking().getHikersInfo().get(0).setAge(102);

        Throwable ex = assertThrows(RuntimeException.class, () -> hikingService.createBooking(bookingRequest));
        assertEquals("Hiker age for this Trail should be between 5 years to 100 years.", ex.getMessage());
    }
}