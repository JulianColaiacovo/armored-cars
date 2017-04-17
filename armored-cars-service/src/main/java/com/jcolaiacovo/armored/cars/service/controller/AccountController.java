package com.jcolaiacovo.armored.cars.service.controller;

import com.jcolaiacovo.armored.cars.api.model.ChangePasswordDTO;
import com.jcolaiacovo.armored.cars.domain.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Julian on 17/04/2017.
 */
@RestController
@RequestMapping("/accounts")
public class AccountController {

    private AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/change-password")
    public ChangePasswordDTO login(@RequestBody ChangePasswordDTO changePasswordDTO) {
        this.accountService.changePassword(changePasswordDTO.getUserName(),
                changePasswordDTO.getOldPassword(),
                changePasswordDTO.getNewPassword(),
                changePasswordDTO.getRepeatNewPassword());

        return changePasswordDTO;
    }

}
