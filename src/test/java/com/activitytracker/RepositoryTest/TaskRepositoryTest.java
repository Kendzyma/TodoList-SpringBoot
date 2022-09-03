package com.activitytracker.RepositoryTest;

import com.activitytracker.Model.Task;
import com.activitytracker.Repository.TaskRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.isA;

@DataJpaTest
public class TaskRepositoryTest {
   @Autowired
   private TaskRepository taskRepository;
   @Autowired
  private TestEntityManager entityManager;
@Test
public void save_a_task(){
    Task task = new Task();
    task.setTitle("Yea");
    task.setDescription("This is me");
    Task actual = taskRepository.save(task);
    Assertions.assertThat(actual).hasFieldOrPropertyWithValue("yea","This is me");

}

}
