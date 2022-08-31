package com.activitytracker.utils;

import java.time.LocalDate;

public class DateTimeComp {
    public static String compareDate(LocalDate currentDate, LocalDate dueDate){
        if((currentDate.isAfter(dueDate) || currentDate.isEqual(dueDate))){
            return "Success";
        }
        return "Invalid";
    }
}
