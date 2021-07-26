package com.hike.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Trail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer trail_Id;
    @NonNull
    @Column(name = "NAME", unique = true, nullable = false)
    private String name;
    @NonNull
    @Column(name = "START_AT", unique = true, nullable = false)
    private String startAt;
    @NonNull
    @Column(name = "END_AT", unique = true, nullable = false)
    private String endAt;
    @NonNull
    @Column(name = "MINIMUM_AGE", nullable = false)
    private Integer minimumAge;
    @NonNull
    @Column(name = "MAXIMUM_AGE", nullable = false)
    private Integer maximumAge;
    @NonNull
    @Column(name = "UNIT_PRICE", nullable = false)
    private Double unitPrice;
}
