package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;

import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DbService dbService;

    @MockBean
    private TaskMapper taskMapper;

    @Test
    public void shouldGetTasks() throws Exception {
        //Given
        List<TaskDto> taskDtoList = new ArrayList<>();
        TaskDto taskDto = new TaskDto(1L, "Test Title", "Test content");
        taskDtoList.add(taskDto);

        List<Task> taskList = new ArrayList<>();
        Task task = new Task(1L, "Test Title", "Test content");
        taskList.add(task);

        when(taskMapper.mapToTaskDtoList(taskList)).thenReturn(taskDtoList);
        when(dbService.getAllTasks()).thenReturn(taskList);
        //When & Then
        mockMvc.perform(get("/v1/task/getTasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].title", is("Test Title")))
                .andExpect(jsonPath("$[0].content", is("Test content")));
    }

    @Test
    public void shouldGetTask() throws Exception {
        //Given
        TaskDto taskDto = new TaskDto(1L, "Test Title", "Test content");

        Task task = new Task(1L, "Test Title", "Test content");


        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);
        when(dbService.getTask(1L)).thenReturn(Optional.of(task));

        //When & Then
        mockMvc.perform(get("/v1/task/getTask")
                .param("taskId", "1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("Test Title")))
                .andExpect(jsonPath("$.content", is("Test content")));
    }

    @Test
    public void deleteTask() throws Exception {
        //Given
        Task task = new Task(1L, "Test Title", "Test content");
        //When & Then
        mockMvc.perform(delete("/v1/task/deleteTask")
        .contentType(MediaType.APPLICATION_JSON)
        .param("taskId", "1"))
                .andExpect(status().isOk());



    }

    @Test
    public void updateTask() throws Exception{
        //Given
        TaskDto taskDto = new TaskDto(1L, "Test Title", "Test content");
        Task task = new Task(1L, "Test Title", "Test content");

        Task updatedTask = new Task (1L, "Updated Title", "Updated content");
        TaskDto updatedTaskDto = new TaskDto(1L, "Updated Title", "Updated content");

       when(taskMapper.mapToTaskDto(updatedTask)).thenReturn(updatedTaskDto);
        when(taskMapper.mapToTask(taskDto)).thenReturn(task);
        when(dbService.saveTask(task)).thenReturn(updatedTask);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);
        //When & Then
        mockMvc.perform(put("/v1/task/updateTask")
        .contentType(MediaType.APPLICATION_JSON)
        .content(jsonContent))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("Updated Title")))
                .andExpect(jsonPath("$.content", is("Updated content")));
    }

   @Test
    public void shouldCreateTask() throws Exception {
        //Given
        Task task = new Task(1L, "Creating Task", "Test content");

        TaskDto taskDto = new TaskDto(1L, "Creating Task", "Test content");

       when(taskMapper.mapToTask(taskDto)).thenReturn(task);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);
        //When & Then
        mockMvc.perform(post("/v1/task/createTask")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());


    }
}