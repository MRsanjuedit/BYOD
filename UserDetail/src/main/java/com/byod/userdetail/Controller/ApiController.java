package com.byod.userdetail.Controller;

import com.byod.userdetail.Model.Api;
import com.byod.userdetail.Repository.ApiRepository;
import com.byod.userdetail.Repository.UserRepository;
import com.byod.userdetail.Service.ApiService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.String;

@RestController
@RequestMapping("/{userId}")
public class ApiController {
    @Autowired
    private final ApiService apiService;
    public ApiController(ApiService apiService) {
        this.apiService = apiService;
    }

    @PostMapping("/create-api")
    @Tag(name = "Create an API", description = "It create an APIs for the user")
    public ResponseEntity<Api> createApiForUser(@RequestBody String apiName, @PathVariable String userId) {
        return new ResponseEntity<>(apiService.createApiForUser(userId,apiName,60), HttpStatus.CREATED);
    }
    @DeleteMapping("/{api}/delete-api")
    @Tag(name = "Delete API" , description = "Delete an API of the Specific User ")
    public ResponseEntity<String> deleteApiForUser(@PathVariable String userId, @PathVariable String api) {
        return new ResponseEntity<>(apiService.deleteApiForUser(userId,api),HttpStatus.NO_CONTENT);
    }
}
