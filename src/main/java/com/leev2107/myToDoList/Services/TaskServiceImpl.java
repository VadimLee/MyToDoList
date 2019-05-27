package com.leev2107.myToDoList.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leev2107.myToDoList.Entities.Task;
import com.leev2107.myToDoList.Repositories.TaskRepository;

@Service
public class TaskServiceImpl implements TaskService {

	TaskRepository taskRepository;

	@Autowired
	public TaskServiceImpl(TaskRepository theRepository) {
		this.taskRepository = theRepository;

	}

	@Override
	public List<Task> getTasks() {

		return taskRepository.findAll();
	}

	@Override
	public Task getTask(int id) {
		if (taskRepository.existsById(id)) {
			Optional<Task> tempTask = taskRepository.findById(id);
			Task task = tempTask.get();
			return task;
		}
		return null;
	}

	@Override
	public Task deleteTask(int taskId) {
		Task tempTask = taskRepository.getOne(taskId);
		taskRepository.deleteById(taskId);
		return tempTask;
	}

	@Override
	public Task saveTask(Task task) {
		return taskRepository.save(task);
	}

	@Override
	public Task changeTaskStatus(int id) {
		if (taskRepository.existsById(id)) {
			Optional<Task> tempTask = taskRepository.findById(id);
			Task task = tempTask.get();

			if (task.getStatus() == true) {
				task.setStatus(false);
			} else {
				task.setStatus(true);
			}
			return taskRepository.save(task);
		} else
			return null;
	}

	@Override
	public List<Task> getTaskByDayOfWeek(int dayOfWeek) {

		return taskRepository.getTaskByDayOfWeek(dayOfWeek);
	}

	@SuppressWarnings("deprecation")
	@Override
	public String getDayName(int day) {
		String dayName = "";
		switch (day) {
		case 0:
			dayName = "Sunday";
			break;
		case 1:
			dayName = "Monday";
			break;
		case 2:
			dayName = "Tuesday";
			break;
		case 3:
			dayName = "Wednesday";
			break;
		case 4:
			dayName = "Thursday";
			break;
		case 5:
			dayName = "Friday";
			break;
		case 6:
			dayName = "Saturday";
			break;
		}
		if (day == new java.util.Date().getDay())
			dayName = "Today";
		return dayName;
	}
}
