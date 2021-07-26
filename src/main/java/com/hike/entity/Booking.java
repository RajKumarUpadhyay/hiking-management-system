package com.hike.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import jdk.nashorn.internal.runtime.regexp.joni.exception.ErrorMessages;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Getter
    @Setter
    @Column(name = "TRAIL_ID", nullable = false)
    private Integer trail_id;

    @Getter
    @Setter
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @Column(name = "BOOKING_DATE_TIME")
    @FutureOrPresent
    private LocalDateTime bookingDateAndTime;

    @Getter
    @Setter
    @Column(name = "TOTAL_BOOKING_AMOUNT")
    private double totalBookingAmount;

    @NotEmpty
    @Getter
    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL)
    private List<HikerInfo> hikersInfo;

    public void setHikersInfo(List<HikerInfo> hikersInfo) {
        this.hikersInfo = hikersInfo;
        for(HikerInfo info : hikersInfo) {
            info.setBooking(this);
        }
    }
}
