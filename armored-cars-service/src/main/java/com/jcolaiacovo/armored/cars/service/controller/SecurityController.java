package com.jcolaiacovo.armored.cars.service.controller;

import com.jcolaiacovo.armored.cars.domain.login.SecurityToken;
import com.jcolaiacovo.armored.cars.domain.login.User;
import com.jcolaiacovo.armored.cars.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

/**
 * Created by ffeller on 11/2/16.
 */
@Controller
@RequestMapping("/security")
public class SecurityController {

    @Autowired
    private SecurityService securityService;

    @ResponseBody
    @RequestMapping(method = PUT)
    public SecurityToken login(@RequestBody User user) {
        return this.securityService.login(user.getEmail(), user.getPassword());
    }

    @ResponseBody
    @RequestMapping(method = DELETE)
    public void logout(@RequestParam String token) {
        this.securityService.logout(token);
    }
}
