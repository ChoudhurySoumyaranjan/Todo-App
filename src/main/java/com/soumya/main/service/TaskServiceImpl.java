package com.soumya.main.service;

import com.soumya.main.entity.Task;
import com.soumya.main.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements  TaskService{
    @Autowired
    private TaskRepository taskRepository;


    @Override
    public Page<Task> getallTasks(Integer pageNo, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNo,pageSize);
        Page<Task> page =taskRepository.findAll(pageable);
        return page;
    }

    @Override
    public boolean saveTasks(Task task) {
        //setting Date
        LocalDate localDate=LocalDate.now();
        task.setDate(localDate.toString());

        //Setting taskCompleted 0(Not Completed) ByDefault
        task.setIsCompleted(0);
        try{
            taskRepository.save(task);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteTask(Long id) {
        try {
            taskRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateCompletitionStatus(Long id, int code) {
            Optional<Task> optionalTask=taskRepository.findById(id);
            try {
                if (optionalTask.isPresent()){
                    Task task=optionalTask.get();
                    task.setIsCompleted(code);
                    taskRepository.save(task);
                }
                return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
