package fr.fms.todolist;

import static org.junit.jupiter.api.Assertions.assertEquals;

//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;

import fr.fms.todolist.dao.CategoryRepository;
import fr.fms.todolist.dao.TaskRepository;
import fr.fms.todolist.entities.Category;
import fr.fms.todolist.entities.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

class TodolistApplicationTests {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
	void contextLoads() {

	}

    @Test
    void testCreateTask(){
        //Create dummy category and article for test
        Category category_test = categoryRepository.save(new Category("category_test"));
        taskRepository.save(new Task("name_test", LocalDate.now(),"description_test", category_test));

        // Fetch the Test item from database
        Task task_test = taskRepository.findByNameContainingIgnoreCase("name_test").get(0);

        //Check if items are the same
        assertEquals("name_test", task_test.getName());
    }

}
