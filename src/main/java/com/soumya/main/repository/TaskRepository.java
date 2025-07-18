package com.soumya.main.repository;

import com.soumya.main.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {
//    @Query(value = "select * from task",nativeQuery = true)
//    Optional<List<Task>> findAllTasks();
}
