package fr.fms.todolist.web;

import fr.fms.todolist.dao.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TaskController {
    @Autowired
    TaskRepository taskRepository;

    @GetMapping("/index")
    public String index(){
        return "home";
    }
}
