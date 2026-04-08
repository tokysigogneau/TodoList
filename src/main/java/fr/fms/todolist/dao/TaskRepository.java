package fr.fms.todolist.dao;

import fr.fms.todolist.entities.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
    Page<Task> findByCategoryId(Long id, Pageable pageable);
    Page<Task> findByCategoryIdAndNameContains(Long id, String name, Pageable pageable);
    Page<Task> findByNameContains(String name, Pageable pageable);
    void deleteById(Long Id);
}
