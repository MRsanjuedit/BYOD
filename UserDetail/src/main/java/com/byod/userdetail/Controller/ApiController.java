package com.byod.userdetail.Controller;

import com.byod.userdetail.Model.Api;
import com.byod.userdetail.Repository.ApiRepository;
import com.byod.userdetail.Repository.UserRepository;
import com.byod.userdetail.Service.ApiService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/{userId}")
public class ApiController {
    private final ApiService apiService;

    public ApiController(ApiService apiService) {
        this.apiService = apiService;
    }

    @PostMapping("/create-api")
    @Tag(name = "Create an API", description = "It create an APIs for the user")
    public ResponseEntity<Api> createApiForUser(@RequestBody String apiName, @PathVariable UUID userId) {
        return new ResponseEntity<>(apiService.createApiForUser(userId,apiName,60), HttpStatus.CREATED);
    }
    @DeleteMapping("/{userId}/{api}/delete-api")
    @Tag(name = "Delete API" , description = "Delete an API of the Specific User ")
    public ResponseEntity<String> deleteApiForUser(@PathVariable UUID userId, @PathVariable UUID apiId) {
        return new ResponseEntity<>(apiService.deleteApiForUser(userId,apiId),HttpStatus.NO_CONTENT);
    }
}
