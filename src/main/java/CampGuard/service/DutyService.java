package CampGuard.service;

import CampGuard.entity.Duty;
import CampGuard.repository.DutyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DutyService {

    private final DutyRepository dutyRepository;

    public DutyService(DutyRepository dutyRepository) {
        this.dutyRepository = dutyRepository;
    }

    public Duty addDuty(Duty duty) {
        return dutyRepository.save(duty);
    }

    public List<Duty> getAllDuties() {
        return dutyRepository.findAll();
    }

    public Optional<Duty> getDutyById(Long id) {
        return dutyRepository.findById(id);
    }

    public Duty updateDuty(Long id, Duty updatedDuty) {

        Duty existing = dutyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Duty not found"));

        existing.setDutyName(updatedDuty.getDutyName());
        existing.setAssignedTo(updatedDuty.getAssignedTo());
        existing.setDutyDate(updatedDuty.getDutyDate());
        existing.setShift(updatedDuty.getShift());
        existing.setLocation(updatedDuty.getLocation());
        existing.setStatus(updatedDuty.getStatus());

        return dutyRepository.save(existing);
    }

    public void deleteDuty(Long id) {
        dutyRepository.deleteById(id);
    }
}