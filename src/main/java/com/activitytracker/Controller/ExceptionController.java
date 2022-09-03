package com.activitytracker.Controller;

import com.activitytracker.Exceptions.TaskNotFoundException;
import com.activitytracker.Exceptions.UserNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(UserNotFoundException.class)
    public String userNoFoundException(UserNotFoundException ex){
        return "error";
    }
    @ExceptionHandler(TaskNotFoundException.class)
    public String TaskNotFound(TaskNotFoundException ex){
        return "error";
    }
}
