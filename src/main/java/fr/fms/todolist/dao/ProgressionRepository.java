package fr.fms.todolist.dao;

import fr.fms.todolist.entities.Progression;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgressionRepository extends JpaRepository<Progression, Long> {
}
