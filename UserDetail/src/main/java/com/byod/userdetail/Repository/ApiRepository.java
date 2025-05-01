package com.byod.userdetail.Repository;

import com.byod.userdetail.Model.Api;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ApiRepository extends JpaRepository<Api, UUID> {
    Boolean existsByName(String name);
    Boolean existsByIdAndUserId(UUID id, UUID userId);
    int deleteByUserIdAndId(UUID userId, UUID id);

}
