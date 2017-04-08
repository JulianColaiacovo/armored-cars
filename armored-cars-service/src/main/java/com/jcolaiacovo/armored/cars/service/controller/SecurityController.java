package com.jcolaiacovo.armored.cars.service.controller;

import com.jcolaiacovo.armored.cars.domain.login.SecurityToken;
import com.jcolaiacovo.armored.cars.domain.login.User;
import com.jcolaiacovo.armored.cars.domain.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/security")
public class SecurityController {

    private SecurityService securityService;

    @Autowired
    public SecurityController(SecurityService securityService) {
        this.securityService = securityService;
    }

    @PutMapping
    public SecurityToken login(@RequestBody User user) {
        return this.securityService.login(user.getUserName(), user.getPassword());
    }

    @DeleteMapping
    public void logout(@RequestParam String token) {
        this.securityService.logout(token);
    }

}
