package CampGuard.controller;

import CampGuard.entity.Duty;
import CampGuard.service.DutyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/duties")
@CrossOrigin(origins = "http://localhost:5173")
public class DutyController {

    private final DutyService dutyService;

    public DutyController(DutyService dutyService) {
        this.dutyService = dutyService;
    }

    @PostMapping
    public Duty addDuty(@RequestBody Duty duty) {
        return dutyService.addDuty(duty);
    }

    @GetMapping
    public List<Duty> getAllDuties() {
        return dutyService.getAllDuties();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Duty> getDutyById(@PathVariable Long id) {
        return dutyService.getDutyById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Duty> updateDuty(
            @PathVariable Long id,
            @RequestBody Duty duty) {

        try {
            return ResponseEntity.ok(
                    dutyService.updateDuty(id, duty)
            );
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDuty(@PathVariable Long id) {
        dutyService.deleteDuty(id);
        return ResponseEntity.noContent().build();
    }
}