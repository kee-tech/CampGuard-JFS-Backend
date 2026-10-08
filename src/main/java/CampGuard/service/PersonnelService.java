package CampGuard.service;

import CampGuard.entity.Personnel;
import CampGuard.repository.PersonnelRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonnelService {

    private final PersonnelRepository personnelRepository;

    public PersonnelService(PersonnelRepository personnelRepository) {
        this.personnelRepository = personnelRepository;
    }

    // Add personnel
    public Personnel addPersonnel(Personnel personnel) {
        return personnelRepository.save(personnel);
    }

    // Get all personnel
    public List<Personnel> getAllPersonnel() {
        return personnelRepository.findAll();
    }

    // Get personnel by ID
    public Optional<Personnel> getPersonnelById(Long id) {
        return personnelRepository.findById(id);
    }

    // Update personnel
    public Personnel updatePersonnel(Long id, Personnel updatedPersonnel) {

        Personnel existing = personnelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Personnel not found"));

        existing.setServiceNumber(updatedPersonnel.getServiceNumber());
        existing.setName(updatedPersonnel.getName());
        existing.setRank(updatedPersonnel.getRank());
        existing.setUnit(updatedPersonnel.getUnit());
        existing.setContactNumber(updatedPersonnel.getContactNumber());
        existing.setEmail(updatedPersonnel.getEmail());

        return personnelRepository.save(existing);
    }

    // Delete personnel
    public void deletePersonnel(Long id) {
        personnelRepository.deleteById(id);
    }
}