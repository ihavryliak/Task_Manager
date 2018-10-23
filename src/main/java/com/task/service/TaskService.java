package com.task.service;

import com.task.domain.Task;
import com.task.domain.User;

import java.util.List;

public interface TaskService {

    Task create (Task task);
    Task findById (Long id);
    void deleteById (Long id);
    List<Task> findAll ();
    void share (User user, Long id);
}
