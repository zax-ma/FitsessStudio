package com.example.userservice.service.user.api;

import com.example.userservice.dto.PageDTO;
import com.example.userservice.dto.UserAdminDTO;
import com.example.userservice.dto.UserDTO;
import org.springframework.data.domain.Pageable;

import java.sql.Timestamp;
import java.util.UUID;

public interface IUserAdminService {

    void createUser(UserAdminDTO userCreationAdminDto);
    PageDTO<UserDTO> getUserPage(Pageable pageable);

    UserDTO getUserInfo(UUID uuid);

   void updateUser(UUID uuid, Timestamp dt_update, UserAdminDTO user);

}
