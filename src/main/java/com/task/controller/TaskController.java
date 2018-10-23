package com.task.controller;

import com.task.domain.Task;
import com.task.domain.User;
import com.task.service.TaskServiceImpl;
import com.task.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TaskController {

    @Autowired
    private TaskServiceImpl taskService;

    @Autowired
    private UserService userService;

    @GetMapping("/task")
    public String task() {
        return "task";
    }

    @GetMapping("/task/create")
    public ModelAndView task(Model tasks) {
        return new ModelAndView("createTask", "tasks", new Task());
    }

    @PostMapping("/task/create")
    public String createTask(@AuthenticationPrincipal User user,
                             @ModelAttribute Task task,
                             Model tasks) {
        Task newTask = new Task(user, task.getName(), task.getStatus(), task.getPriority(), task.getText());
        this.taskService.create(newTask);
        return "task";
    }

    @GetMapping("/task/display")
    public String displayTaskList(Model tasks) {
        tasks.addAttribute("tasks", this.taskService.findAll());
        return "taskList";
    }

    @GetMapping("/task/edit")
    public ModelAndView edit(Model tasks) {
        tasks.addAttribute("tasks", this.taskService.findAll());
        return new ModelAndView("editTask", "task", new Task());
    }

    @PostMapping("task/edit")
    public String editTask(@ModelAttribute Task task,
                           Model tasks) {
            Task taskToUpdate = this.taskService.findById(task.getId());
            taskToUpdate.setName(task.getName());
            taskToUpdate.setStatus(task.getStatus());
            taskToUpdate.setPriority(task.getPriority());
            taskToUpdate.setText(task.getText());
            Task updatedTask = this.taskService.create(taskToUpdate);
            tasks.addAttribute("tasks", taskService.findAll());
        return "taskList";
    }

    @GetMapping("/task/delete")
    public ModelAndView delete(Model tasks) {
        tasks.addAttribute("tasks", this.taskService.findAll());
        return new ModelAndView("deleteTask", "task", new Task());
    }

    @PostMapping("task/delete")
    public String deleteTask(@ModelAttribute Task task,
                             Model tasks) {
            this.taskService.deleteById(task.getId());
            tasks.addAttribute("tasks", taskService.findAll());
        return "taskList";
    }

    @GetMapping("task/share")
    public ModelAndView share(Model users, Model tasks) {
        users.addAttribute("users", userService.findAll());
        tasks.addAttribute("tasks",taskService.findAll());
        return new ModelAndView("shareTask","task",new Task());
    }

    @PostMapping("task/share")
    public String shareTask(
            @AuthenticationPrincipal User user,
            @ModelAttribute Task task,
            Model tasks,
            Model users) {
            tasks.addAttribute("tasks", taskService.findAll());
            users.addAttribute("users", userService.findAll());
            this.taskService.share(user, task.getId());
        return "task";
    }
}
