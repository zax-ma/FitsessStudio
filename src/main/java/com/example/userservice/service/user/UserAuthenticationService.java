package com.example.userservice.service.user;

import com.example.userservice.dao.entity.UserEntity;
import com.example.userservice.dao.repo.IUserRepository;
import com.example.userservice.dto.EUserRole;
import com.example.userservice.dto.LoginDTO;
import com.example.userservice.dto.UserDTO;
import com.example.userservice.service.user.api.IUserAuthenticationService;
import com.example.userservice.utils.exceptions.SingleErrorResponse;
import jakarta.validation.ValidationException;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserAuthenticationService implements IUserAuthenticationService {

    IUserRepository repository;
    private Converter<UserEntity, UserDTO> toDtoConverter;
    private Converter<UserEntity, EUserRole> Roleconverter;

    private BCryptPasswordEncoder encoder;

    public UserAuthenticationService(IUserRepository repository,
                                     Converter<UserEntity, UserDTO> toDtoConverter,
                                     Converter<UserEntity, EUserRole> roleconverter,
                                     BCryptPasswordEncoder encoder) {
        this.repository = repository;
        this.toDtoConverter = toDtoConverter;
        Roleconverter = roleconverter;
        this.encoder = encoder;
    }

    @Override
    public UserDTO getMyInfo() {

/*        UserEntity userInfo = this.repository.findById(uuid)
                .orElseThrow(() -> new SingleErrorResponse("User with uuid " + uuid + " was not found"));
        return this.toDtoConverter.convert(userInfo);*/
        return null;
    }

    @Override
    public void login(LoginDTO loginDTO) {
        UserEntity user = repository.findByMail(loginDTO.getMail());
        if(user != null){
            if(encoder.matches(loginDTO.getPassword(), user.getPassword())){
                //jwt generation
            } throw new ValidationException("Wrong password");
        }throw new ValidationException("Wrong e-mail");
    }

    @Override
    public UserEntity findByMail(String mail) {
        UserEntity user = repository.findByMail(mail);
        if(user == null){
            throw new SingleErrorResponse("User with mail" + mail + " was not found");
        }
        return user;
    }
}
