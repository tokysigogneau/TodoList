package fr.fms.todolist.web;

import fr.fms.todolist.dao.CategoryRepository;
import fr.fms.todolist.dao.ProgressionRepository;
import fr.fms.todolist.dao.TaskRepository;
import fr.fms.todolist.dao.UserRepository;
import fr.fms.todolist.entities.Category;
import fr.fms.todolist.entities.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
public class TaskController {
    @Autowired
    TaskRepository taskRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ProgressionRepository progressionRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/index")
    public String index(){
        return "home";
    }

    // Article by category
    @GetMapping("/my_tasks")
    public String categoryTask(Model model,
                               @RequestParam(name="page", defaultValue = "0")int page,
                               @RequestParam (name="category",defaultValue = "1") Long cat_id
    ){
        // list of all categories
        List<Category> categories = categoryRepository.findAll();

        //List of task by category, format : Page
        Page<Task> cat_task = taskRepository.findByCategoryId( cat_id , PageRequest.of(page, 5));

        model.addAttribute("listTasks", cat_task.getContent());
        model.addAttribute("category_list", categories);

        model.addAttribute("pages", new int [cat_task.getTotalPages()]);

        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", cat_id);
        return "my_tasks";
    }

    //Display needed info to create a new task
    @GetMapping("/create_task")
    public String newTask(Model model) {
        model.addAttribute("task", new Task());
        model.addAttribute("category_list", categoryRepository.findAll());
        return "create_task";
    }

    // create a new task
    @PostMapping("/save")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public String save(@Valid Task task, BindingResult bindingResult, Model model) {

        if(bindingResult.hasErrors()) {
            return "create_task";
        }

        taskRepository.save(task);
        return "redirect:/index";
    }



}
