package com.leev2107.myToDoList.Controllers;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.leev2107.myToDoList.Entities.Task;
import com.leev2107.myToDoList.Services.TaskService;

import junit.framework.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RestControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private TaskService service;

	// get a home/ specific day page
	@Test
	@WithMockUser(username = "admin", password = "123", roles = "EMPLOYEE")
	public void getList() throws Exception {
		// local data
		Task mockTask = new Task(0, "first", 1, "Description 1", false);
		Task mockTask2 = new Task(1, "second", 1, "Description 2", false);
		List<Task> mockList = new ArrayList<Task>();
		mockList.add(mockTask);
		mockList.add(mockTask2);

		Mockito.when(service.getTaskByDayOfWeek(Mockito.anyInt())).thenReturn(mockList);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/").accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		String expected = "[{\"id\":0,\"title\":\"first\",\"dayOfWeek\":1,\"description\":\"Description 1\",\"status\":false},{\"id\":1,\"title\":\"second\",\"dayOfWeek\":1,\"description\":\"Description 2\",\"status\":false}]\r\n";

		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);

	}

	// get task by Id
	@Test
	@WithMockUser(username = "admin", password = "123", roles = "ADMIN")
	public void getOne() throws Exception {
		// local data
		Task mockTask = new Task(0, "first", 1, "Description 1", false);

		Mockito.when(service.getTask(Mockito.anyInt())).thenReturn(mockTask);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/task/0").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		String expected = "{\"id\":0,\"title\":\"first\",\"dayOfWeek\":1,\"description\":\"Description 1\",\"status\":false}";

		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);

	}

	// change task status
	@Test
	@WithMockUser(username = "employee", password = "123", roles = "EMPLOYEE")
	public void changeTaskStatus() throws Exception {
		// local data
		Task mockTask = new Task(0, "first", 1, "Description 1", false);

		Mockito.when(service.changeTaskStatus(Mockito.anyInt())).thenReturn(mockTask);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/task/changeStatus/0")
				.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expected = "true";
		Assert.assertEquals(expected, result.getResponse().getContentAsString());
	}

	// create task
	@Test
	@WithMockUser(username = "admin", password = "123", roles = "ADMIN")
	public void createTask() throws Exception {
		// local data
		Task mockTask = new Task(0, "first", 1, "Description 1", false);

		String expected = "{\"id\":0,\"title\":\"first\",\"dayOfWeek\":1,\"description\":\"Description 1\",\"status\":false}";

		Mockito.when(service.saveTask(mockTask)).thenReturn(mockTask);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/task/createTask")
				.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON).content(expected);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);

	}

	// update task
	@Test
	@WithMockUser(username = "admin", password = "123", roles = "ADMIN")
	public void updateTask() throws Exception {
		// local data
		Task mockTask = new Task(0, "first", 1, "Description 1", false);

		String expected = "{\"id\":0,\"title\":\"first\",\"dayOfWeek\":1,\"description\":\"Description 1\",\"status\":false}";

		Mockito.when(service.saveTask(mockTask)).thenReturn(mockTask);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/task/0").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).content(expected);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);

	}

	// update task
	@Test
	@WithMockUser(username = "admin", password = "123", roles = "ADMIN")
	public void deleteTask() throws Exception {
		// local data
		Task mockTask = new Task(0, "first", 1, "Description 1", false);

		String content = "{\"id\":0,\"title\":\"first\",\"dayOfWeek\":1,\"description\":\"Description 1\",\"status\":false}";

		Mockito.when(service.deleteTask(Mockito.anyInt())).thenReturn(mockTask);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/task/0").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).content(content);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		String expected = "true";
		Assert.assertEquals(expected, result.getResponse().getContentAsString());

	}

}