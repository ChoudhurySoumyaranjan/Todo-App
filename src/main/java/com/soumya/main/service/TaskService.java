package com.soumya.main.service;

import com.soumya.main.entity.Task;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

public interface TaskService {
    Page<Task> getallTasks(Integer pageNo,Integer pageSize);

    boolean saveTasks(Task task);
    boolean deleteTask(Long id);
    boolean updateCompletitionStatus(Long id, int code);
}
