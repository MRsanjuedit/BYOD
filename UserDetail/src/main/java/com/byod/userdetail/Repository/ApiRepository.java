package com.byod.userdetail.Repository;

import com.byod.userdetail.Model.Api;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ApiRepository extends JpaRepository<Api, UUID> {
    Boolean existsByName(String name);
    Boolean existsByIdAndUserId(UUID apiId, UUID userId);
    int deleteByUserIdAndApiId(UUID apiId,UUID userId);}
