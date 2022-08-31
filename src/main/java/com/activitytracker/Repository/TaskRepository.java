package com.activitytracker.Repository;

import com.activitytracker.Model.Task;
import com.activitytracker.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task,Integer> {
    Optional<List<Task>> findAllByUser(User user);
    Optional<List<Task>> findTasksByUserAndStatus(User user, String status);
    Task findTasksByUserAndId(User user,int id);


}
