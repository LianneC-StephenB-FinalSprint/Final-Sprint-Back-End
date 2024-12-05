package com.keyin.flight;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<Flight, Long> {
    // Additional custom queries can be added here if needed
}
