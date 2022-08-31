package com.activitytracker.Service.Interface;

import com.activitytracker.Model.User;

public interface UserService {
    boolean registerUser(User user);

    boolean loginAuth(User user);
}
