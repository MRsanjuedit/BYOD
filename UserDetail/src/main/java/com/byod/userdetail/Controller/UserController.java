package com.byod.userdetail.Controller;

import com.byod.userdetail.Model.Api;
import com.byod.userdetail.Model.User;
import com.byod.userdetail.Service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @Tag(name = "Get all users",description = "It will return All the users")
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(),HttpStatus.ACCEPTED);
    }
    @PostMapping("/create-user")
    @Tag(name = "To Create User",description = "It will create User")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
    }
    @PutMapping("/update-user")
    @Tag(name = "Update User", description = "It will update user")
    public ResponseEntity<String> updateUser(@RequestBody User user) {
        return new ResponseEntity<>(userService.updateUser(user), HttpStatus.CREATED);
    }
    @DeleteMapping("/delete-user")
    @Tag(name = "Delete User", description = "It will delete User")
    public ResponseEntity<String> deleteUser(@RequestBody User user) {
        Boolean isDeleted = userService.deleteUser(user);
        if(isDeleted) return new ResponseEntity<>("Deleted Successfully",HttpStatus.OK);
        return new ResponseEntity<>("Deleted Failed",HttpStatus.NOT_FOUND);
    }

}
