package fr.fms.todolist;

import fr.fms.todolist.entities.Category;
import fr.fms.todolist.entities.Task;
import fr.fms.todolist.dao.CategoryRepository;
import fr.fms.todolist.dao.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class ServiceTask {
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional
    public void createTask(String name, LocalDate date, String description, Category category){
        taskRepository.save(new Task(name, date,description, category));
    };

    @Transactional
    public List<Task> findTaskByName(String name){
        return taskRepository.findByNameContainingIgnoreCase(name);
    };

    @Transactional
    public Page<Task> findTaskByCategory(Long categoryId, Pageable pageable){
        return taskRepository.findByCategoryId(categoryId, pageable);
    }

    @Transactional
    public void deleteTaskById(Long id){
        taskRepository.deleteById(id);
    }


}