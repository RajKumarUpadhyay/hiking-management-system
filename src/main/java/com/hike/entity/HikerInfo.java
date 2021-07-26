package com.hike.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HikerInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private int id;

    @NotEmpty(message = "First Name is mandatory")
    @Column(name = "FIRST_NAME", nullable = false,length = 100)
    private String firstName;

    @Column(name = "LAST_NAME", nullable = true, length = 100)
    private String lastName;

    @NonNull
    @Column(name = "AGE", length = 100, nullable = false)
    private int age;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "booking_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Booking booking;
}
