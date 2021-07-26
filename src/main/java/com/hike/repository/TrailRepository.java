package com.hike.repository;

import com.hike.entity.Trail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrailRepository extends JpaRepository<Trail, Integer> {
}
