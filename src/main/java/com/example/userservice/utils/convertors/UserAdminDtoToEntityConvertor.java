package com.example.userservice.utils.convertors;

import com.example.userservice.dao.entity.UserEntity;
import com.example.userservice.dto.AuxFieldsDTO;
import com.example.userservice.dto.UserAdminDTO;
import org.springframework.core.convert.converter.Converter;

public class UserAdminDtoToEntityConvertor implements Converter<UserAdminDTO, UserEntity> {

    private AuxFieldsDTO auxFieldsDTO;

    @Override
    public UserEntity convert(UserAdminDTO source) {
        return new UserEntity(auxFieldsDTO.getUuid(),
                source.getMail(),
                source.getFio(),
                source.getPassword(),
                source.getRole(),
                source.getStatus(),
                auxFieldsDTO.getDt_create(),
                auxFieldsDTO.getDt_update());
    }
}