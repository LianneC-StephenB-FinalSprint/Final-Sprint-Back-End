package com.keyin.gate;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GateRepository extends JpaRepository<Gate, Long> {
    // You can add custom query methods here if needed
}
