package com.keyin.airline;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AirlineRepository extends JpaRepository<Airline, Long> {
    // Add custom query methods if needed
}
