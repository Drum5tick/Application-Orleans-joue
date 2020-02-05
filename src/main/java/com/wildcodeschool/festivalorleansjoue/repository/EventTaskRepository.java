package com.wildcodeschool.festivalorleansjoue.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wildcodeschool.festivalorleansjoue.entity.EventTask;

@Repository
public interface EventTaskRepository extends JpaRepository<EventTask, Long> {
	
	public List<EventTask> findByTaskId(Long taskId);
	public List<EventTask> findByEventId(Long eventId);

}
