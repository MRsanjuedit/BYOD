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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Transactional
public class ApiServiceImplements implements ApiService {

    private final UserRepository userRepository;
    private final ApiRepository apiRepository;

    @Autowired
    public ApiServiceImplements(UserRepository userRepository, ApiRepository apiRepository) {
        this.userRepository = userRepository;
        this.apiRepository = apiRepository;
    }

    @Override
    public Api createApiForUser(UUID userId, String apiName, long expiryMinutes) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserIdNotFoundException(userId));

        boolean nameExists = user.getApis() != null &&
                user.getApis().stream()
                        .anyMatch(api -> api.getName().equalsIgnoreCase(apiName));

        if (nameExists) {
            throw new SameApiNameExistsException(apiName);
        }

        Api api = new Api();
        api.setName(apiName);
        api.setUser(user);
        api.setExpiresAt(LocalDateTime.now().plusMinutes(expiryMinutes));

        // No need to update user.apis manually unless explicitly needed
        return apiRepository.save(api);
    }

    @Override
    public String deleteApiForUser(UUID userId, UUID apiId) {
        // Delete directly based on userId and apiId; skip pre-checks
        int deletedCount = apiRepository.deleteByUserIdAndId(userId, apiId);

        if (deletedCount == 0) {
            throw new ApiNotFoundException("API not found or does not belong to the user.");
        }

        return "API deleted successfully";
    }
}
