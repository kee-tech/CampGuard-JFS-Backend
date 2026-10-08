package CampGuard.repository;

import CampGuard.entity.Duty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DutyRepository extends JpaRepository<Duty, Long> {
}