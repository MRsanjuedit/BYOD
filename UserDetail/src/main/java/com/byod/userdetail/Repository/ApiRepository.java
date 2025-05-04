package com.byod.userdetail.Repository;

import com.byod.userdetail.Model.Api;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.lang.String;

@Repository
public interface ApiRepository extends JpaRepository<Api, String> {
    Boolean existsByName(String name);
    Boolean existsByIdAndUserId(String id, String userId);
    int deleteByUserIdAndId(String userId, String id);

}
