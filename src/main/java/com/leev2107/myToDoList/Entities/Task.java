package com.leev2107.myToDoList.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

@Entity
@Table(name = "tasks")
public class Task {

	@Column(name = "id")
	@javax.persistence.Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "title")
	private String title;
	@Column(name = "day_of_week")
	private int dayOfWeek;
	@Column(name = "description")
	private String description;
	@Column(name = "status")
	private Boolean status;

	public Task() {

	};

	public Task(String title, int dayOfWeek, String description, Boolean status) {
		this.title = title;
		this.dayOfWeek = dayOfWeek;
		this.description = description;
		this.status = status;
	}

	public Task(int id, String title, int dayOfWeek, String description, Boolean status) {
		super();
		this.id = id;
		this.title = title;
		this.dayOfWeek = dayOfWeek;
		this.description = description;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(int dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Task [id=" + id + ", title=" + title + ", dayOfWeek=" + dayOfWeek + ", description=" + description
				+ ", status=" + status + "]";
	}

}
