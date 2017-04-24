package com.jcolaiacovo.armored.cars.service.controller;

import com.jcolaiacovo.armored.cars.api.model.ChangePasswordDTO;
import com.jcolaiacovo.armored.cars.api.model.ClientDTO;
import com.jcolaiacovo.armored.cars.api.model.UserDTO;
import com.jcolaiacovo.armored.cars.domain.login.SecurityToken;
import com.jcolaiacovo.armored.cars.domain.model.Client;
import com.jcolaiacovo.armored.cars.domain.model.User;
import com.jcolaiacovo.armored.cars.domain.service.SecurityService;
import com.jcolaiacovo.armored.cars.domain.service.UserService;
import com.jcolaiacovo.armored.cars.domain.transformer.UserTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;
    private UserTransformer userTransformer;

    @Autowired
    public UserController(UserService userService, UserTransformer userTransformer) {
        this.userService = userService;
        this.userTransformer = userTransformer;
    }

    @GetMapping
    public List<UserDTO> getAll() {
        List<User> users = this.userService.getAll();
        return this.userTransformer.transformToDTOAll(users);
    }

    @GetMapping("/{id}")
    public UserDTO getById(@PathVariable int id) {
        User user = this.userService.getById(id);
        return this.userTransformer.transformToDTO(user);
    }

    @GetMapping("/name/{name}")
    public UserDTO getById(@PathVariable String name) {
        User user = this.userService.getByName(name);
        return this.userTransformer.transformToDTO(user);
    }

    @PostMapping
    public UserDTO save(@RequestBody UserDTO userDTO) {
        User user = this.userTransformer.transform(userDTO);
        this.userService.save(user);
        return this.userTransformer.transformToDTO(user);
    }

    @PostMapping("/change-password")
    public ChangePasswordDTO login(@RequestBody ChangePasswordDTO changePasswordDTO) {
        this.userService.changePassword(changePasswordDTO.getUserName(),
                changePasswordDTO.getOldPassword(),
                changePasswordDTO.getNewPassword());

        return changePasswordDTO;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        this.userService.delete(id);
    }

}
