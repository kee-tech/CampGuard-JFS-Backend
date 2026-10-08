package CampGuard.controller;

import CampGuard.entity.Personnel;
import CampGuard.service.PersonnelService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/personnel")
public class PersonnelController {

    private final PersonnelService personnelService;

    public PersonnelController(PersonnelService personnelService) {
        this.personnelService = personnelService;
    }

    // ADD PERSONNEL
    @PostMapping
    public Personnel addPersonnel(@RequestBody Personnel personnel) {
        return personnelService.addPersonnel(personnel);
    }

    // GET ALL PERSONNEL
    @GetMapping
    public List<Personnel> getAllPersonnel() {
        return personnelService.getAllPersonnel();
    }

    // GET PERSONNEL BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Personnel> getPersonnelById(@PathVariable Long id) {

        return personnelService.getPersonnelById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // UPDATE PERSONNEL
    @PutMapping("/{id}")
    public ResponseEntity<Personnel> updatePersonnel(
            @PathVariable Long id,
            @RequestBody Personnel personnel) {

        try {
            return ResponseEntity.ok(
                    personnelService.updatePersonnel(id, personnel)
            );
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE PERSONNEL
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePersonnel(@PathVariable Long id) {

        personnelService.deletePersonnel(id);

        return ResponseEntity.noContent().build();
    }
}