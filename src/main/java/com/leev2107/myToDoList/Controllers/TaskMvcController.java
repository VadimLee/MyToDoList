package com.leev2107.myToDoList.Controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.leev2107.myToDoList.Entities.Task;
import com.leev2107.myToDoList.Services.TaskService;

@Controller
public class TaskMvcController {
	@Autowired
	TaskService taskService;

	// Get today tasks
	@GetMapping("/")
	String showTasks(Model theModel) {
		@SuppressWarnings("deprecation")
		int dayId = new java.util.Date().getDay();
		theModel.addAttribute("tasks", taskService.getTaskByDayOfWeek(dayId));
		theModel.addAttribute("dayOfWeek", taskService.getDayName(dayId));
		return "mvc-home";
	}

	// get selected day tasks
	@GetMapping("/{dayId}")
	String getSpecificDayTasks(@PathVariable int dayId, Model theModel) {
		theModel.addAttribute("tasks", taskService.getTaskByDayOfWeek(dayId));
		theModel.addAttribute("dayOfWeek", taskService.getDayName(dayId));
		return "mvc-home";
	}

	// change status of selected task
	@PostMapping("/task/changeStatus/{taskId}")
	String updateTask(@PathVariable int taskId, HttpServletRequest request) {
		String referer = request.getHeader("Referer");
		taskService.changeTaskStatus(taskId);
		return "redirect:" + referer;
	}

	// delete selected task
	@PostMapping("/task/delete/{taskId}")
	String DeleteTask(@PathVariable int taskId, HttpServletRequest request) {
		String referer = request.getHeader("Referer");
		taskService.deleteTask(taskId);
		return "redirect:" + referer;
	}

	// show update form
	@PostMapping("/task/update/{taskId}")
	String updateTask(@PathVariable int taskId, Model theModel) {
		theModel.addAttribute("task", taskService.getTask(taskId));
		return "taskForm";
	}

	// show create form
	@GetMapping("/task/create")
	String createTask(Model theModel) {
		Task tempTask = new Task();
		tempTask.setStatus(false);
		theModel.addAttribute("task", tempTask);
		return "taskForm";
	}

	// save create/update form
	@PostMapping("/task/saveTask")
	public String saveTask(@ModelAttribute("task") Task task) {
		taskService.saveTask(task);
		return "redirect:/" + task.getDayOfWeek();

	}

}
