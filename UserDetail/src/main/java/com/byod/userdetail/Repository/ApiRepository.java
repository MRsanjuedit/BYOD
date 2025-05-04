package com.byod.userdetail.Repository;

import com.byod.userdetail.Model.Api;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.lang.String;

@Repository
public interface ApiRepository extends JpaRepository<Api, Long> {
    Boolean existsByName(String name);
    Boolean existsByIdAndUserId(Long id, Long userId);
    int deleteByUserIdAndId(Long userId, Long id);

}
