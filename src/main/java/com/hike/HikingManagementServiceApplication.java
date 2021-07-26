package com.hike;

import com.hike.entity.Trail;
import com.hike.repository.TrailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class HikingManagementServiceApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(HikingManagementServiceApplication.class, args);
	}

	@Autowired
	TrailRepository trailRepository;

	@Override
	public void run(String... args) throws Exception {
		Trail trail = new Trail();
		trail.setTrail_Id(1);
		trail.setName("Shire");
		trail.setStartAt("07:00");
		trail.setEndAt("09:00");
		trail.setMinimumAge(5);
		trail.setMaximumAge(100);
		trail.setUnitPrice(29.90);
		trailRepository.save(trail);

		Trail trail1 = new Trail();
		trail1.setTrail_Id(2);
		trail1.setName("Gondor");
		trail1.setStartAt("10:00");
		trail1.setEndAt("13:00");
		trail1.setMinimumAge(11);
		trail1.setMaximumAge(50);
		trail1.setUnitPrice(59.90);
		trailRepository.save(trail1);

		Trail trail3 = new Trail();
		trail3.setTrail_Id(3);
		trail3.setName("Mordor");
		trail3.setStartAt("14:00");
		trail3.setEndAt("19:00");
		trail3.setMinimumAge(18);
		trail3.setMaximumAge(40);
		trail3.setUnitPrice(99.90);
		trailRepository.save(trail3);
	}
}
