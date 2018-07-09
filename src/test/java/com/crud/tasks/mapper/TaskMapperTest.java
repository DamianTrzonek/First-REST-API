package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskMapperTest {
@Autowired
private TaskMapper taskMapper;


    @Test
    public void mapToTask() {
        //Given
        TaskDto taskDto = new TaskDto(1L, "test", "testing mapper");
        Task task = new Task(1L, "test", "testing mapper");
        //When
       Task testTask = taskMapper.mapToTask(taskDto);
       //Then
        assertEquals(testTask, task);
    }

    @Test
    public void mapToTaskDto() {
        //Given
        Task task = new Task(1L, "test", "testing mapper");
        TaskDto taskDto = new TaskDto(1L, "test", "testing mapper");
        //When
        TaskDto testTaskDto = taskMapper.mapToTaskDto(task);
        //Then
        assertEquals(testTaskDto, taskDto);
    }

    @Test
    public void mapToTaskDtoList() {
        //Given
        List<Task> taskList = new ArrayList<>();
        Task task = new Task(1L, "test", "testing mapper");
        Task task2 = new Task();
        taskList.add(task);
        taskList.add(task2);

        List<TaskDto> taskDtoList = new ArrayList<>();
        TaskDto taskDto = new TaskDto(1L, "test", "testing mapper");
        TaskDto taskDto2 = new TaskDto();
        taskDtoList.add(taskDto);
        taskDtoList.add(taskDto2);
        //When
        List<TaskDto> testTaskDtoList = taskMapper.mapToTaskDtoList(taskList);
        //Then
        assertEquals(testTaskDtoList, taskDtoList);
        assertEquals(2, testTaskDtoList.size());
        assertEquals(testTaskDtoList.size(), taskDtoList.size());

    }
}