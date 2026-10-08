package CampGuard.service;

import CampGuard.entity.Equipment;
import CampGuard.repository.EquipmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EquipmentService {

    private final EquipmentRepository equipmentRepository;

    public EquipmentService(EquipmentRepository equipmentRepository) {
        this.equipmentRepository = equipmentRepository;
    }

    // Add equipment
    public Equipment addEquipment(Equipment equipment) {
        return equipmentRepository.save(equipment);
    }

    // Get all equipment
    public List<Equipment> getAllEquipment() {
        return equipmentRepository.findAll();
    }

    // Get equipment by ID
    public Optional<Equipment> getEquipmentById(Long id) {
        return equipmentRepository.findById(id);
    }

    // Update equipment
    public Equipment updateEquipment(Long id, Equipment updatedEquipment) {

        Equipment existing = equipmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Equipment not found"));

        existing.setEquipmentName(updatedEquipment.getEquipmentName());
        existing.setEquipmentCode(updatedEquipment.getEquipmentCode());
        existing.setCategory(updatedEquipment.getCategory());
        existing.setQuantity(updatedEquipment.getQuantity());
        existing.setStatus(updatedEquipment.getStatus());

        return equipmentRepository.save(existing);
    }

    // Delete equipment
    public void deleteEquipment(Long id) {
        equipmentRepository.deleteById(id);
    }
}