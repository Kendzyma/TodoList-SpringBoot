package com.activitytracker.Controller;

import com.activitytracker.Model.Status;
import com.activitytracker.Model.Task;
import com.activitytracker.Repository.TaskRepository;
import com.activitytracker.Service.Interface.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/Task")

public class TaskController {
    @Autowired
    private TaskService taskService;

    /*
     * Create new Task
     */
    @GetMapping("/create")
    public String getCreate(Model model, HttpSession session) {
        if (session.getAttribute("Auth") == null) return "redirect:/account/login";
        model.addAttribute("createTask", new Task());
        return "CreateTask";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("createTask") Task task, Model model, HttpSession session) {
        String email = (String) session.getAttribute("Auth");
        taskService.createTask(task, email);
        model.addAttribute("success", "Task successfully created");
        return "CreateTask";
    }

    /*
     * View all task
     */
    @GetMapping("/all-task")
    public String viewAllTask(HttpSession session, Model model) {
        if (session.getAttribute("Auth") == null) return "redirect:/account/login";

        List<Task> taskList = taskService.getAllTask((String) session.getAttribute("Auth"));
        model.addAttribute("taskList", taskList);
        return "ViewTask";
    }

    /*
     * View specific task
     */
    @GetMapping("/task/{id}")
    public String viewTask(@PathVariable int id, HttpSession session, Model model) {
        if (session.getAttribute("Auth") == null) return "redirect:/account/login";
        Task task = taskService.getTask(id);
        model.addAttribute("taskList", task);
        return "ViewTask";
    }

    /*
     * Edit task
     */
    @GetMapping("/edit-task/{id}")
    public String showEditTask(@PathVariable int id, HttpSession session, Model model) {
        if (session.getAttribute("Auth") == null) return "redirect:/account/login";
        Task task = taskService.getTask(id);
        model.addAttribute("editTask", task);
        return "EditTask";
    }

    /*
     * Edit task
     */
    @PostMapping("/edit-task/{id}")
    public String editTask(@PathVariable int id, @ModelAttribute("editTask") Task task, HttpSession session) {
        if (session.getAttribute("Auth") == null) return "redirect:/account/login";
        taskService.editTask(id, task);
        return "redirect:/";
    }

    /*
     * Delete Task
     */
    @GetMapping("delete-task/{Page}/{id}")
    public String deleteTask(@PathVariable("id") int id, @PathVariable("Page") String page, HttpSession session) {
        if (session.getAttribute("Auth") == null) return "redirect:/account/login";
        if (page.equalsIgnoreCase("pending")) {
            taskService.deleteTask(id);
            return "redirect:/Task/pending-task";
        } else if (page.equalsIgnoreCase("inprogress")) {
            taskService.deleteTask(id);
            return "redirect:/Task/in-progress-task";
        } else if (page.equalsIgnoreCase("Completed")) {
            taskService.deleteTask(id);
            return "redirect:/Task/all-completed-task";
        } else {
            taskService.deleteTask(id);
            return "redirect:/Task/all-task";
        }
    }

    /*
     * View pending Task
     */
    @GetMapping("pending-task")
    public String viewPendingTask(HttpSession session, Model model) {
        if (session.getAttribute("Auth") == null) return "redirect:/account/login";
        List<Task> taskList = taskService.getPendingTask((String) session.getAttribute("Auth"));
        model.addAttribute("pendingList", taskList);
        return "PendingListPage";
    }

    /*
     * View Done Task
     */
    @GetMapping("all-completed-task")
    public String viewCompletedTask(HttpSession session, Model model) {
        if (session.getAttribute("Auth") == null) return "redirect:/account/login";
        List<Task> taskList = taskService.getCompletedTask((String) session.getAttribute("Auth"));
        model.addAttribute("completedList", taskList);
        return "CompletedTask";
    }

    @GetMapping("/completeTask/{id}/{page}")
    public String markAsComplete(@PathVariable("id") int id, @PathVariable("page") String page, HttpSession session) {
        if (session.getAttribute("Auth") == null) return "redirect:/account/login";
        taskService.completeTask(id);
        if (page.equals("Pending")) return "redirect:/pending-task";
        else if (page.equals("ViewTask")) return "redirect:/all-task";
        return null;
    }


    /*
     * View all in progress Task
     */
    @GetMapping("in-progress-task")
    public String viewInProgressTask(HttpSession session, Model model) {
        if (session.getAttribute("Auth") == null) return "redirect:/account/login";
        List<Task> taskList = taskService.getInProgressTask((String) session.getAttribute("Auth"));
        model.addAttribute("inProgress", taskList);
        return "InProgressTask";
    }


    @GetMapping("/move-to-done/{page}/{id}")
    public String moveToPending(@PathVariable("id") int id, @PathVariable("page") String page, Model model, HttpSession session) {

        if (page.equalsIgnoreCase("pending")) {
            taskService.moveToDone((String) session.getAttribute("Auth"), id);
            return "redirect:/Task/pending-task";
        } else if (page.equalsIgnoreCase("inprogress")) {
            taskService.moveToDone((String) session.getAttribute("Auth"), id);
            return "redirect:/Task/in-progress-task";
        } else {
            taskService.moveToDone((String) session.getAttribute("Auth"), id);
            return "redirect:/Task/all-task";
        }
    }

    /*
     * Move task to pending from in progress
     */
    @GetMapping("move-to-pending/{page}/{id}")
    public String moveTask(@PathVariable("id") int id, @PathVariable("page") String page, Model model, HttpSession session) {
        if (page.equalsIgnoreCase("pending")) {
            taskService.moveToPending((String) session.getAttribute("Auth"), id);
            return "redirect:/Task/pending-task";
        } else if (page.equalsIgnoreCase("inprogress")) {
            taskService.moveToPending((String) session.getAttribute("Auth"), id);
            return "redirect:/Task/in-progress-task";
        } else {
            taskService.moveToPending((String) session.getAttribute("Auth"), id);
            return "redirect:/Task/all-task";
        }
    }

    /*
     * Search for specific task
     */
    @PostMapping("/search-task")
    public String searchPage(@ModelAttribute("task") Task task, Model model) {
        Optional<List<Task>> searchResult = taskService.searchTask(task);
        model.addAttribute("searchResult", searchResult.get());
        return "SearchPage";
    }

}


