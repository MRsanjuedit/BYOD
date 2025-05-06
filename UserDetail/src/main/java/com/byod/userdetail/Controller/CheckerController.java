package com.byod.userdetail.Controller;

import com.byod.userdetail.Service.CheckerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/check/{userId}/{apiId}")
public class CheckerController {
    CheckerService checkerService;

    public CheckerController(CheckerService checkerService) {
        this.checkerService = checkerService;
    }

    @GetMapping
    public Boolean check(@PathVariable Long userId, @PathVariable Long apiId) {
        return checkerService.isValidUserIdandApiId(userId,apiId);
    }
}
