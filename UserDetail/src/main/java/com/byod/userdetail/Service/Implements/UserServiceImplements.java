package com.byod.userdetail.Service.Implements;

import com.byod.userdetail.Exceptions.EmailAlreadyExistsException;
import com.byod.userdetail.Exceptions.UserNotFoundException;
import com.byod.userdetail.Model.User;
import com.byod.userdetail.Repository.UserRepository;
import com.byod.userdetail.Service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class UserServiceImplements implements UserService {

    private final UserRepository userRepository;
    UserServiceImplements(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    @Override
    public User createUser(User user) {
        if(userRepository.existsByEmail(user.getEmail())) {
            throw new EmailAlreadyExistsException(user.getEmail());
        }
        return userRepository.save(user);
    }

    @Override
    public String updateUser(User user) {
        if(!userRepository.existsByEmail(user.getEmail())) throw new UserNotFoundException(user.getEmail());
        userRepository.save(user);
        return "User updated";
    }

    @Override
    public Boolean deleteUser(User user) {
        if(!userRepository.existsByEmail(user.getEmail())) {
            throw new UserNotFoundException(user.getEmail());
        }
        userRepository.deleteById(user.getId());
        return true;

    }
}
