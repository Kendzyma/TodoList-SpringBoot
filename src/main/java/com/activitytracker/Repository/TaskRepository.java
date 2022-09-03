package com.activitytracker.Repository;

import com.activitytracker.Model.Task;
import com.activitytracker.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;
@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
    Optional<List<Task>> findAllByUser(User user);

    Optional<List<Task>> findTasksByUserAndStatus(User user, String status);

    Task findTasksByUserAndId(User user, int id);

    @Query(value = "SELECT * from task where title like %?1% or description like %?1%", nativeQuery = true)
    Optional<List<Task>> findTasksByTitle(String title);
}
