package com.example.userservice.dto;

import com.example.userservice.utils.validation.annotation.CharSize;
import com.example.userservice.utils.validation.annotation.EmailPattern;
import com.example.userservice.utils.validation.annotation.NotEmptyNorBlank;
import com.example.userservice.utils.validation.annotation.RoleParams;



public class UserAdminDTO {
    @NotEmptyNorBlank
    @EmailPattern("^(.+)@(.+)$") //   ^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$
    private String mail;
    @NotEmptyNorBlank
    private String fio;
    @RoleParams
    private UserRole role;
    private UserStatus status;
    @NotEmptyNorBlank
    @CharSize(min = "3", max = "16")
    private String password;
    public UserAdminDTO(){
    }

    public String getMail() {
        return mail;
    }

    public String getFio() {
        return fio;
    }

    public UserRole getRole() {
        return role;
    }

    public UserStatus getStatus() {
        return status;
    }

    public String getPassword() {
        return password;
    }

    public static AdminBuilder create() {
        return new UserAdminDTO().new AdminBuilder();
    }
    public class AdminBuilder{
        public AdminBuilder(){
        }

        public AdminBuilder setFio(String fio) {
            UserAdminDTO.this.fio = fio;
            return this;
        }
        public AdminBuilder setMail(String mail) {
            UserAdminDTO.this.mail = mail;
            return this;
        }
        public AdminBuilder setRole(UserRole role) {
            UserAdminDTO.this.role = role;
            return this;
        }
        public AdminBuilder setStatus(UserStatus status) {
            UserAdminDTO.this.status = status;
            return this;
        }
        public AdminBuilder setPassword(String password) {
            UserAdminDTO.this.password = password;
            return this;
        }

        public UserAdminDTO build() {
            return UserAdminDTO.this;
        }

        @Override
        public String toString() {
            return "AdminUserBuilder{" +
                    "mail='" + mail + '\'' +
                    ", fio='" + fio + '\'' +
                    ", role=" + role +
                    ", status=" + status +
                    ", password='" + password + '\'' +
                    '}';
        }



    }

    @Override
    public String toString() {
        return "UserAdminDto{" +
                "mail='" + mail + '\'' +
                ", fio='" + fio + '\'' +
                ", role=" + role +
                ", status=" + status +
                ", password='" + password + '\'' +
                '}';
    }
}
