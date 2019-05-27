package com.leev2107.myToDoList.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leev2107.myToDoList.Entities.Task;
import com.leev2107.myToDoList.Services.TaskService;

@RestController
@RequestMapping("/api")
public class TaskRestController {

	@Qualifier("taskServiceImpl")
	@Autowired
	TaskService taskService;

	// get today tasks

	@GetMapping("/")
	List<Task> getAllTasks() {
		@SuppressWarnings("deprecation")
		int dayId = new java.util.Date().getDay();
		return taskService.getTaskByDayOfWeek(dayId);
	}

	// get selected day tasks
	@GetMapping("/{dayId}")
	List<Task> getTasksByDayOfWeek(@PathVariable int dayId) {

		return taskService.getTaskByDayOfWeek(dayId);
	}

	// change status of selected task
	@PostMapping("/task/changeStatus/{taskId}")
	Boolean updateStatus(@PathVariable int taskId) {

		taskService.changeTaskStatus(taskId);
		return true;

	}

	// create task
	@PostMapping("/task/createTask")
	public Task addTask(@RequestBody Task tempTask) {
		tempTask.setId(0);
		taskService.saveTask(tempTask);
		return tempTask;
	}

	// update task
	@PostMapping("/task/{taskId}")
	Task updateTask(@RequestBody Task task, @PathVariable int taskId) {
		task.setId(taskId);
		taskService.saveTask(task);
		return task;
	}

	// delete task
	@DeleteMapping("/task/{taskId}")
	Boolean deleteTask(@PathVariable int taskId) {
		taskService.deleteTask(taskId);
		return true;
	}

	// get selected task by id
	@GetMapping("/task/{taskId}")
	Task getTask(@PathVariable int taskId) {
		Task tempTask = taskService.getTask(taskId);
		return tempTask;
	}

}
