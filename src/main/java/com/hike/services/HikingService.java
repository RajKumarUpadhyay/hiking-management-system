package com.hike.services;

import com.hike.dto.BookingRequest;
import com.hike.dto.BookingResponse;
import com.hike.entity.Booking;
import com.hike.entity.Trail;
import com.hike.exception.NoSuchElementFoundException;
import com.hike.repository.BookingRepository;
import com.hike.repository.TrailRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.chrono.ChronoLocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@AllArgsConstructor
public class HikingService {

    private final TrailRepository trailRepository;
    private final BookingRepository bookingRepository;

    public List<Trail> getTrails() {
        return trailRepository.findAll();
    }

    public Trail getTrailById(Integer id) {
        return trailRepository.findById(id).orElseThrow(()->
                new NoSuchElementFoundException("No item found for trail id : "+ id));
    }

    public BookingResponse getBookingById(Integer id) {
        System.out.println("Booking Id : "+ id);
        Booking booking = bookingRepository.findById(id).orElseThrow(()->
                new NoSuchElementFoundException("No reservation found for booking id : "+ id));
        Trail trail = trailRepository.getById(booking.getTrail_id());
        BookingResponse bookingResponse =  new BookingResponse();
        bookingResponse.setBooking(booking);
        bookingResponse.setTrail(trail);
        System.out.println("Booking response for booing id : "+ id +  " is : "+bookingResponse.toString());
        return bookingResponse;
    }

    public void createBooking(BookingRequest bookingRequest) throws ParseException {
        System.out.println("Booking Request : "+ bookingRequest);
        Optional<Trail> trail = trailRepository.findById(bookingRequest.getBooking().getTrail_id());
        trail.orElseThrow(() ->
                new NoSuchElementFoundException("Invalid Booking Request. Provided trail doesn't exists!"));

        validateCriteriaForHiking(trail.get(), bookingRequest.getBooking());
        bookingRepository.save(bookingRequest.getBooking());
        System.out.println("Booking request completed");
    }

    public void cancelBooking(Integer id) {
        bookingRepository.findById(id).ifPresent(x -> bookingRepository.deleteById(id));
    }

    private void validateCriteriaForHiking(Trail trail, Booking booking) throws ParseException {
        AtomicInteger totalHikers = new AtomicInteger();
        double tailAmount = trail.getUnitPrice();
        int minAge = trail.getMinimumAge();
        int maxAge = trail.getMaximumAge();
        String startTime = trail.getStartAt();
        String endTime = trail.getEndAt();

        DateFormat datos = new SimpleDateFormat("HH:mm");
        Date date = datos.parse(startTime);
        Date date1 = datos.parse(booking.getBookingDateAndTime().getHour() +":"+booking.getBookingDateAndTime().getMinute());

        if (date.before(date1) && booking.getBookingDateAndTime().toLocalDate().isEqual(LocalDate.now()))
            throw new RuntimeException("Booking for this trail is not possible because timeslot already passed for today");

        booking.getHikersInfo().forEach(book -> {
            totalHikers.getAndIncrement();
            if (book.getAge() > maxAge || book.getAge() < minAge)
                throw new RuntimeException("Hiker age for this Trail should be between "+ minAge +" years to "+ maxAge + " years.");
        });
        booking.setTotalBookingAmount(Math.round(tailAmount*totalHikers.get()));
    }
}
