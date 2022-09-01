package com.activitytracker.Service;

import com.activitytracker.Model.User;
import com.activitytracker.Repository.UserRepository;
import com.activitytracker.Service.Interface.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImp implements UserService {
    @Autowired
    private UserRepository userRepository;

    /*
     * Register User implementation
     */
    @Override
    public boolean registerUser(User user) {
        Optional<User> tmpuser = userRepository.findUserByEmail(user.getEmail());
        if (tmpuser.isEmpty()) {
            userRepository.save(user);
            return true;
        }
        return false;
    }

    /*
     * user login User implementation
     */
    @Override
    public boolean loginAuth(User user) {
        Optional<User> tmpUser = userRepository.findUserByEmailAndPassword(user.getEmail(), user.getPassword());
        if (tmpUser.isEmpty()) return false;
        else {
            return tmpUser.get().getEmail().equals(user.getEmail()) &&
                    tmpUser.get().getPassword().equals(user.getPassword());
        }
    }
}
