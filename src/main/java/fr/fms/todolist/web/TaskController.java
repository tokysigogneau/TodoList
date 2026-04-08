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
import org.springframework.web.bind.annotation.PathVariable;
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
                               @RequestParam (name="category",defaultValue = "0") Long cat_id,
                               @RequestParam (name="keyword",defaultValue = "") String keyword
    ){
        // list of all categories
        List<Category> categories = categoryRepository.findAll();

        Page<Task> cat_task;
        //List of task by category, format : Page
        if (cat_id==0L){
            cat_task = taskRepository.findByNameContains(keyword  , PageRequest.of(page, 5));
        } else {
            cat_task = taskRepository.findByCategoryIdAndNameContains(  cat_id, keyword  , PageRequest.of(page, 5));
        }


        model.addAttribute("listTasks", cat_task.getContent());
        model.addAttribute("category_list", categories);
        model.addAttribute("pages", new int [cat_task.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);
        model.addAttribute("cat_id", cat_id);

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

    //Edit task

    @GetMapping("/edit_task/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {

        //there is an error without the ".orElseThrow()"
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid task id: " + id));

        model.addAttribute("task", task);
        model.addAttribute("category_list", categoryRepository.findAll());
        model.addAttribute("progression_list", progressionRepository.findAll());

        return "edit_task";
    }


    @PostMapping("/edit_task/{id}")
    public String edit(@PathVariable Long id,
                       @Valid Task task,
                       BindingResult bindingResult,
                       Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("category_list", categoryRepository.findAll());
            model.addAttribute("progression_list", progressionRepository.findAll());
            return "edit_task";
        }
        Task bddTask = taskRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid task id: " + id));

        bddTask.setName(task.getName());
        bddTask.setDescription(task.getDescription());
        bddTask.setCategory(task.getCategory());
        bddTask.setProgression(task.getProgression());

        taskRepository.save(bddTask);

        return "redirect:/my_tasks";
    }



}
