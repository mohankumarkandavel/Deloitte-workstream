package service;

import model.Task;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * API endpoints for accessing and managing tasks
 */
@RestController
public class TaskController {

    @RequestMapping("/tasks")
    List<Task> getTasks() {
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task());
        tasks.add(new Task());
        return null;
    }

    @RequestMapping("/task/{id}")
    Task getTask(@PathVariable int id) {
        return new Task();
    }

    //@RequestMapping(value = "/task", method = RequestMethod.POST)
    //ResponseEntity<?> addNewTask() {
    //  
    // }

}
