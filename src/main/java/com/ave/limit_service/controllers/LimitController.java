package com.ave.limit_service.controllers;

import com.ave.limit_service.requests.WithdrawRequestDto;
import com.ave.limit_service.response.UserDto;
import com.ave.limit_service.services.LimitService;
import org.springframework.web.bind.annotation.*;

@RestController
public class LimitController {
    private final LimitService limitService;

    public LimitController(LimitService limitService) {
        this.limitService = limitService;
    }

    @GetMapping("/limits/{userId}")
    public UserDto findUserById(@PathVariable String userId) {
        return limitService.findUser(userId);
    }

    @PostMapping("/limit/withdraw")
    public void withdraw(@RequestBody WithdrawRequestDto request) {
        limitService.withdraw(request);
    }
}
