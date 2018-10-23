package com.task.service;

import com.task.domain.Task;
import com.task.domain.User;
import com.task.repository.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepo taskRepo;

    @Override
    public Task create(Task task) {
       return taskRepo.save(task);
    }

    @Override
    public Task findById(Long id) {
        return taskRepo.getOne(id);
    }

    @Override
    public void deleteById (Long id) {
        taskRepo.deleteById(id);
    }

    @Override
    public List<Task> findAll() {
        return this.taskRepo.findAll();
    }

    @Override
    public void share(User user, Long id) {
    //send task to user's board
    }
}
