package com.byod.userdetail.Service;

import com.byod.userdetail.Model.User;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
public interface UserService {
    public User createUser(User user);
    public String updateUser(User user);
    public Boolean deleteUser(User user);

    List<User> getAllUsers();
}
