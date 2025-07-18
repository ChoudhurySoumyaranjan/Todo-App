package com.soumya.main.controller;

import com.soumya.main.entity.Task;
import com.soumya.main.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping({"/","/index"})
    public String openIndexPageAndGetTasks(Model model,@RequestParam(value = "pageNo", defaultValue = "1",required = false)Integer pageNum){
        int pageSize=5; //default Page Size
        int pageNo = pageNum-1;
        Page<Task> page= taskService.getallTasks(pageNo,pageSize);

        model.addAttribute("taskObj",new Task());
        if (!page.isEmpty()){
            List<Task> taskList=page.getContent();
            model.addAttribute("listTask",taskList);
            model.addAttribute("currentPage",pageNum);
            System.out.println(pageNo);
            model.addAttribute("totalPage",page.getTotalPages());
            return "task-page";
        }
            model.addAttribute("msg","No Tasks Found");
                return "task-page";
    }

    @PostMapping("/handleTaskForm")
    public String createTask(@ModelAttribute("TaskObj")Task task){
        boolean success=taskService.saveTasks(task);
        return "redirect:/";
    }

    @GetMapping("/delete")
    public String deleteTaskById(@RequestParam("id")Long id,
                                 RedirectAttributes redirectAttributes){
        boolean success = taskService.deleteTask(id);
        if (success){
            redirectAttributes.addFlashAttribute("success","Task Deleted  Successfully");
        }else {
            redirectAttributes.addFlashAttribute("failed","Task Deletion  Unsuccessful");
        }
        return "redirect:/";
    }
    @GetMapping("/toggle")
    public String updateCompleteStatus(
                                        @RequestParam("id")Long id,
                                        @RequestParam("complete")int code
    ){
        taskService.updateCompletitionStatus(id,code);
        return "redirect:/";
    }
}
