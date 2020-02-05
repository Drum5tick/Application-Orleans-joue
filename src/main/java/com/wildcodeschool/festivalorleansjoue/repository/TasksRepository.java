package com.wildcodeschool.festivalorleansjoue.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.wildcodeschool.festivalorleansjoue.entity.Tasks;

@Repository
public interface TasksRepository extends JpaRepository<Tasks, Long>{
	@Modifying
	@Transactional
	@Query(value="DELETE FROM event_tasks WHERE task_id=:taskId", nativeQuery = true)
		void deleteEventsTasks(@Param("taskId") Long taskId);
}
