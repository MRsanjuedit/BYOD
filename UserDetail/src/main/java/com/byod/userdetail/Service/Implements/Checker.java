package com.byod.userdetail.Service.Implements;

import com.byod.userdetail.Exceptions.ApiNotFoundException;
import com.byod.userdetail.Exceptions.UserNotFoundException;
import com.byod.userdetail.Repository.ApiRepository;
import com.byod.userdetail.Repository.UserRepository;
import com.byod.userdetail.Service.CheckerService;
import org.springframework.stereotype.Service;

@Service
public class Checker implements CheckerService {

    UserRepository userRepository;
    ApiRepository apiRepository;

    public Checker(UserRepository userRepository, ApiRepository apiRepository) {
        this.userRepository = userRepository;
        this.apiRepository = apiRepository;
    }

    @Override
    public Boolean isValidUserIdandApiId(Long userId, Long apiId) {
        return isValidApiId(apiId) && isValidUserId(userId);
    }

    @Override
    public Boolean isValidApiId(Long apiId) {
        if(!apiRepository.existsById(apiId)) throw new ApiNotFoundException("Api Id not found");
        return true;
    }

    @Override
    public Boolean isValidUserId(Long userId) {
        if(!userRepository.existsById(userId)) throw new UserNotFoundException("User Id Not Found");
        return true;
    }
}
