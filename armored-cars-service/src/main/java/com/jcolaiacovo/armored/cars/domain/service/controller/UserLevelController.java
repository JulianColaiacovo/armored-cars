package com.jcolaiacovo.armored.cars.service.controller;

import com.jcolaiacovo.armored.cars.domain.model.UserLevel;
import com.jcolaiacovo.armored.cars.service.UserLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Julian on 29/01/2017.
 */
@RestController
@RequestMapping("/user-level")
public class UserLevelController {

    private UserLevelService userLevelService;

    @Autowired
    public UserLevelController(UserLevelService userLevelService) {
        this.userLevelService = userLevelService;
    }

    @GetMapping
    public List<UserLevel> getAllDocumentTypes() {
        return this.userLevelService.getAllUserLevels();
    }

}
