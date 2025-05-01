package com.byod.userdetail.Service.Implements;

import com.byod.userdetail.Exceptions.ApiNotFoundException;
import com.byod.userdetail.Exceptions.SameApiNameExistsException;
import com.byod.userdetail.Exceptions.UserIdNotFoundException;
import com.byod.userdetail.Model.Api;
import com.byod.userdetail.Model.User;
import com.byod.userdetail.Repository.ApiRepository;
import com.byod.userdetail.Repository.UserRepository;
import com.byod.userdetail.Service.ApiService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Service
@Transactional
public class ApiServiceImplements implements ApiService {

    private final UserRepository userRepository;
    private final ApiRepository apiRepository;
    ApiServiceImplements(UserRepository userRepository, ApiRepository apiRepository) {
        this.userRepository = userRepository;
        this.apiRepository = apiRepository;
    }

    @Override
    public Api createApiForUser(UUID userId, String apiName, long expiryMinutes) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserIdNotFoundException(userId));
        if((user.getApis() != null) && user.getApis().stream().anyMatch(api -> api.getName().equalsIgnoreCase(apiName))) throw new SameApiNameExistsException(apiName);
        Api api = new Api();
        api.setName(apiName);
        api.setUser(user);

        if (user.getApis() == null) user.setApis(new ArrayList<>());
        user.getApis().add(api);
        userRepository.save(user);

        return apiRepository.save(api);
    }

    @Override
    public String deleteApiForUser(UUID userId, UUID apiId) {
        if (!userRepository.existsById(userId)) {
            throw new UserIdNotFoundException(userId);
        }
        if (!apiRepository.existsByIdAndUserId(apiId, userId)) {
            throw new ApiNotFoundException("API not found or does not belong to the user.");
        }
        int deletedCount = apiRepository.deleteByUserIdAndApiId(apiId, userId);
        if (deletedCount > 0) {
            return "API deleted successfully";
        } else {
            return "Unable to delete API";
        }
    }

}
