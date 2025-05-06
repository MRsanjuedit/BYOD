package com.byod.userdetail.Service;

import org.springframework.stereotype.Service;

@Service
public interface CheckerService {
    Boolean isValidUserIdandApiId(Long userId, Long apiId);
    Boolean isValidApiId(Long apiId);
    Boolean isValidUserId(Long userId);
}
