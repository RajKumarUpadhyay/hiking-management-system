package com.hike.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.hike.dto.BookingRequest;
import com.hike.entity.Booking;
import com.hike.entity.HikerInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.hamcrest.Matchers.containsString;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@SpringBootTest
@AutoConfigureMockMvc
class HikingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    BookingRequest bookingRequest = new BookingRequest();
    Booking booking = new Booking();
    HikerInfo info  = new HikerInfo();
    List<HikerInfo> hikersInfo = new ArrayList<>();

    ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
    String tempBookingRequest;

    @BeforeEach
    void setUp() throws JsonProcessingException {
        booking.setTrail_id(1);
        booking.setBookingDateAndTime(LocalDateTime.now().plusDays(1));
        info.setAge(30);
        info.setFirstName("Tom");
        hikersInfo.add(info);
        booking.setHikersInfo(hikersInfo);

        bookingRequest.setBooking(booking);
        tempBookingRequest = ow.writeValueAsString(bookingRequest);
    }

    @Test
    void getAllTrails() throws Exception {
        this.mockMvc.perform(get("/api/v1/hiking")).andExpect(status().isOk())
                .andExpect(content().string(containsString("Shire")));
    }

    @Test
    void getTrailsById() throws Exception {
        this.mockMvc.perform(get("/api/v1/hiking/1")).andExpect(status().isOk())
                .andExpect(content().string(containsString("Shire")));
    }

    @Test
    void getBookingById() throws Exception {
        this.mockMvc.perform(post("/api/v1/hiking")
                .contentType(MediaType.APPLICATION_JSON)
                .content(tempBookingRequest)
                .characterEncoding("utf-8"))
                .andExpect(status().isCreated())
                .andReturn();

        this.mockMvc.perform(get("/api/v1/hiking/booking/1")).andExpect(status().isOk())
                .andExpect(content().string(containsString("Tom")));
    }

    @Test
    void createBooking() throws Exception {
        this.mockMvc.perform(post("/api/v1/hiking")
                .contentType(MediaType.APPLICATION_JSON)
                .content(tempBookingRequest)
                .characterEncoding("utf-8"))
                .andExpect(status().isCreated())
                .andReturn();
    }

    @Test
    void cancelBookingById() throws Exception {
        this.mockMvc.perform(delete("/api/v1/hiking/1")).andExpect(status().isOk());
    }
}