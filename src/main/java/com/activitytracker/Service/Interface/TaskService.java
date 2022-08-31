package com.activitytracker.Service.Interface;

import com.activitytracker.Model.Task;

import java.util.List;

public interface TaskService {
    void createTask(Task task, String email);

    List<Task> getAllTask(String email);

    Task getTask(int id);

    void editTask(int id, Task task);

    void deleteTask(int id);

    List<Task> getPendingTask(String email);

    List<Task> getCompletedTask(String email);

    void completeTask(int id);

    List<Task> getInProgressTask(String email);

    void moveToDone(String email,int id);

    void moveToPending(String email, int id);
}
