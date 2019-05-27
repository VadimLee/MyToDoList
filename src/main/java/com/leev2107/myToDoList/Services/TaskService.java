package com.leev2107.myToDoList.Services;

import java.util.List;

import com.leev2107.myToDoList.Entities.Task;

public interface TaskService {

	public List<Task> getTasks();

	public Task getTask(int id);

	public Task deleteTask(int id);

	public Task saveTask(Task task);

	public Task changeTaskStatus(int id);

	public List<Task> getTaskByDayOfWeek(int dayOfWeek);

	public String getDayName(int day);

}
