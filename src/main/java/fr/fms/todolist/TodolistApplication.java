package fr.fms.todolist;

import fr.fms.todolist.dao.CategoryRepository;
import fr.fms.todolist.dao.ProgressionRepository;
import fr.fms.todolist.dao.TaskRepository;
import fr.fms.todolist.dao.UserRepository;
import fr.fms.todolist.entities.Category;
import fr.fms.todolist.entities.Progression;
import fr.fms.todolist.entities.Task;
import fr.fms.todolist.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class TodolistApplication implements CommandLineRunner {
	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private TaskRepository taskRepository;

	@Autowired
	private ProgressionRepository progressionRepository;

	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {

		SpringApplication.run(TodolistApplication.class, args);
	}


	public void run(String... args) throws Exception {
		Category menage = categoryRepository.save(new Category("Menage"));
		Progression todo = progressionRepository.save(new Progression("To do"));
		User firstuser =  userRepository.save(new User("FirstUser"));
		taskRepository.save(new Task("Ranger le salon", new Date(),"les enfants font le bazzar",menage,todo,firstuser));
	}

}
