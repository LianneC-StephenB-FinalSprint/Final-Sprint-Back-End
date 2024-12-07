package com.keyin.gate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/gates")
public class GateController {

    @Autowired
    private GateService gateService;

    @GetMapping
    public List<Gate> getAllGates() {
        return gateService.getAllGates();
    }

    @GetMapping("/{id}")
    public Gate getGateById(@PathVariable Long id) {
        return gateService.getGateById(id);
    }

    @PostMapping
    public Gate addGate(@RequestBody Gate gate) {
        return gateService.addGate(gate);
    }

    @PutMapping("/{id}")
    public Gate updateGate(@PathVariable Long id, @RequestBody Gate gateDetails) {
        return gateService.updateGate(id, gateDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteGate(@PathVariable Long id) {
        gateService.deleteGate(id);
    }
}
