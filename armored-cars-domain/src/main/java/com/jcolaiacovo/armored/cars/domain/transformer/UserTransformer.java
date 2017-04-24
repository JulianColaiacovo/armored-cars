package com.jcolaiacovo.armored.cars.domain.transformer;

import com.jcolaiacovo.armored.cars.api.model.UserDTO;
import com.jcolaiacovo.armored.cars.domain.model.User;
import com.jcolaiacovo.armored.cars.domain.model.UserLevel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

/**
 * Created by Julian on 10/04/2017.
 */
@Component
public class UserTransformer extends AbstractApiTransformer<User, UserDTO> {

    @Override
    public User transform(UserDTO userDTO) {
        User user = new User();

        user.setId(userDTO.getId());
        user.setUser(userDTO.getUserName());
        user.setPassword(userDTO.getPassword());
        if (StringUtils.isNotBlank(userDTO.getUserLevel())) {
            user.setUserLevel(UserLevel.valueOf(userDTO.getUserLevel()));
        }

        return user;
    }

    @Override
    public UserDTO transformToDTO(User user) {
        UserDTO userDTO = new UserDTO();

        userDTO.setId(user.getId());
        userDTO.setUserName(user.getUser());
        userDTO.setUserLevel(user.getUserLevel().name());
        userDTO.setPassword(user.getPassword());

        return userDTO;
    }

}
