package fr.fms.todolist;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;

import fr.fms.todolist.dao.CategoryRepository;
import fr.fms.todolist.dao.TaskRepository;
import fr.fms.todolist.entities.Category;
import fr.fms.todolist.entities.Task;
import fr.fms.todolist.web.TaskController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.Mockito.*;


import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)

class TodolistApplicationTests {

//    @Autowired
//    private TaskRepository taskRepository;
//
//    @Autowired
//    private CategoryRepository categoryRepository;

    @Mock
    TaskRepository taskRepository;

    @Mock
    CategoryRepository categoryRepository;

    @InjectMocks
    ServiceTask serviceTask;

    //Helpers : objets fictifs réutilisables
    private Category buildCategory(){
        Category category = new Category();
        category.setId(1L);
        category.setName("test_category_name");
        return category;
    };

    private Task buildTask(){
        Task task = new Task();
        task.setName("test_task_name");
        task.setDate(LocalDate.now());
        task.setCategory(buildCategory());
        return task;
    }



    @Test
	void contextLoads() {

	}

//    @Test
//    void testCreateTask(){
//        //Create dummy category and article for test
//        Category category_test = categoryRepository.save(new Category("category_test"));
//        taskRepository.save(new Task("name_test", LocalDate.now(),"description_test", category_test));
//
//        // Fetch the Test item from database
//        Task task_test = taskRepository.findByNameContainingIgnoreCase("name_test").get(0);
//
//        //Check if items are the same
//        assertEquals("name_test", task_test.getName());
//    }


    @Test
    void shouldReturnListOfTasksByName(){

    }

    @Test
    void should_return_list_of_task_by_category() {

        // GIVEN
        Task task1 = buildTask();
        Task task2 = buildTask();

        Pageable pageable = PageRequest.of(0, 10);

        Page<Task> page = new PageImpl<>(Arrays.asList(task1, task2));

        when(taskRepository.findByCategoryId(1L, pageable))
                .thenReturn(page);

        // WHEN
        Page<Task> result = serviceTask.findTaskByCategory(1L, pageable);

        // THEN
        assertThat(result.getContent()).hasSize(2);

        verify(taskRepository, times(1))
                .findByCategoryId(1L, pageable);
    }

    @Test
    void should_delete_task_by_id() {

        // GIVEN
        Long id = 1L;

        // WHEN
        serviceTask.deleteTaskById(id);

        // THEN
        verify(taskRepository, times(1)).deleteById(id);
    }


}
