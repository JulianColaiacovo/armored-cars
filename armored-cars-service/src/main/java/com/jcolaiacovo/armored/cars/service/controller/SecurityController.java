package com.jcolaiacovo.armored.cars.service.controller;

import com.jcolaiacovo.armored.cars.api.model.UserDTO;
import com.jcolaiacovo.armored.cars.domain.login.SecurityToken;
import com.jcolaiacovo.armored.cars.domain.model.User;
import com.jcolaiacovo.armored.cars.domain.service.SecurityService;
import com.jcolaiacovo.armored.cars.domain.transformer.UserTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/security")
public class SecurityController {

    private SecurityService securityService;
    private UserTransformer userTransformer;

    @Autowired
    public SecurityController(SecurityService securityService, UserTransformer userTransformer) {
        this.securityService = securityService;
        this.userTransformer = userTransformer;
    }

    @PutMapping
    public SecurityToken login(@RequestBody UserDTO userDTO) {
        User user = this.userTransformer.transform(userDTO);
        return this.securityService.login(user.getUser(), user.getPassword());
    }

    @DeleteMapping
    public void logout(@RequestParam String token) {
        this.securityService.logout(token);
    }

}
