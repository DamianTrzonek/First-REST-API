package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
 @SpringBootTest
public class DbServiceTest {
@Autowired
private DbService dbService;

@Autowired
private TaskRepository repository;

    @Test
    public void getAllTasks() {
        //Initial cleanup
        dbService.deleteAllTasks();
        //Given
        Task task1 = new Task(1L, "test task", "testing db service");
        Task task2 = new Task(2L, "test task2", "testing db service");
        dbService.saveTask(task1);
        dbService.saveTask(task2);
        //When
        List<Task> taskList = dbService.getAllTasks();
        //Then
        assertEquals(2, taskList.size());
        System.out.println(taskList.get(0));
        System.out.println(taskList.get(1));
        //Cleanup
        dbService.deleteAllTasks();
    }

    /*@Test
    public void getTask() {
        dbService.deleteAllTasks();
        //Given
        Task task = new Task(1L, "test task", "testing db service");
        Long taskId = task.getId();
        repository.save(task);
        //When
        Optional<Task> testTask = dbService.getTask(taskId);
        //Then

    }*/

    /*@Test
    public void deleteTask() {
        //Given
        Task task = new Task(22L, "testing task", "you know");
        dbService.saveTask(task);
        Long taskId = task.getId();
        //When
        dbService.deleteTask(taskId);
        //Then
        List<Task> taskList = dbService.getAllTasks();
        assertNull(taskList);
    }*/
}