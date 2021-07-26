package com.hike.controller;

import com.hike.dto.BookingRequest;
import com.hike.dto.BookingResponse;
import com.hike.entity.Trail;
import com.hike.services.HikingService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;

@Slf4j(topic = "HIKING_CONTROLLER")
@RestController
@RequestMapping(path = "/api/v1/hiking")
@Api
public class HikingController {

    @Autowired
    HikingService hikingService;

    /**
     * Returns all available trails.
     * @return List of Trails
     */
    @GetMapping
    public ResponseEntity<List<Trail>> getAllTrails(){
        System.out.println("GET all available trails.");
        return new ResponseEntity(hikingService.getTrails(), HttpStatus.OK);
    }

    /**
     * Find Trails By Trail ID.
     * @param id
     * @return Trail
     */
    @GetMapping(path = "/{id}")
    public ResponseEntity<Trail> getTrailsById(@PathVariable("id") Integer id){
        System.out.println("GET trail by trail ID.");
        return new ResponseEntity(hikingService.getTrailById(id), HttpStatus.OK);
    }

    /**
     * Find Trail booking by supplying booking id as input parameter.
     * @param id
     * @return BookingResponse
     */
    @GetMapping(path = "/booking/{id}")
    public ResponseEntity<BookingResponse> getBookingById(@PathVariable("id") Integer id){
        System.out.println("GET booking by given booking ID.");
        return new ResponseEntity(hikingService.getBookingById(id), HttpStatus.OK);
    }

    /**
     *  Create booking for Trails.
     * @param bookingRequest
     * @return
     */
    @PostMapping
    public ResponseEntity<HttpStatus> createBooking(@Valid @RequestBody BookingRequest bookingRequest) throws ParseException {
        System.out.println("CREATE booking for trail id :"+bookingRequest.getBooking().getTrail_id());
        hikingService.createBooking(bookingRequest);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    /**
     * Delete or Cancel reservation for Trail by passing reservation id.
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> cancelBookingById(@PathVariable("id") Integer id){
        System.out.println("Cancel booking for booking id :"+id);
        hikingService.cancelBooking(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
