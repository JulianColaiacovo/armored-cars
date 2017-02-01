package com.jcolaiacovo.armored.cars.service;

import com.google.common.collect.Lists;
import com.jcolaiacovo.armored.cars.domain.model.UserLevel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Julian on 29/01/2017.
 */
@Transactional
@Service
public class UserLevelService {

    public UserLevelService() {

    }

    public List<UserLevel> getAllUserLevels() {
        return Lists.newArrayList(UserLevel.values());
    }

}
