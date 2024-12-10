package com.keyin.gate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GateService {

    @Autowired
    private GateRepository gateRepository;

    public List<Gate> getAllGates() {
        return gateRepository.findAll();
    }

    public Gate getGateById(Long id) {
        return gateRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Gate not found"));
    }

    public Gate addGate(Gate gate) {
        return gateRepository.save(gate);
    }

    public Gate updateGate(Long id, Gate gateDetails) {
        Gate gate = gateRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Gate not found"));

        gate.setName(gateDetails.getName());
        gate.setTerminal(gateDetails.getTerminal());
        return gateRepository.save(gate);
    }

    public void deleteGate(Long id) {
        Gate gate = gateRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Gate not found"));
        gateRepository.delete(gate);
    }
}