package com.leev2107.myToDoList.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.leev2107.myToDoList.Entities.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

	@Query("SELECT t FROM Task t WHERE t.dayOfWeek = :dayOfWeek")
	List<Task> getTaskByDayOfWeek(@Param("dayOfWeek") int dayOfWeek);

}
