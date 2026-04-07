package fr.fms.todolist.dao;

import fr.fms.todolist.entities.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
    Page<Task> findByCategoryId(Long id, Pageable pageable);
    Page<Task> findByDescriptionContains(String description, Pageable pageable);
}
