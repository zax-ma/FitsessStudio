package com.example.userservice.dto;

import com.example.userservice.utils.validation.annotation.CharSize;
import com.example.userservice.utils.validation.annotation.EmailPattern;
import com.example.userservice.utils.validation.annotation.NotEmptyNorBlank;


public class UserRegistrationDTO {

 //   @JsonProperty("mail")
    @NotEmptyNorBlank
    @EmailPattern("^[a-zA-Z0-9_+&*-] + (?:\\\\.[a-zA-Z0-9_+&*-] + )*@(?:[a-zA-Z0-9-]+\\\\.) + [a-zA-Z]{2,7}")
    private String mail;
 //   @JsonProperty("fio")

    @NotEmptyNorBlank
    private String fio;
//    @JsonProperty("password")

    @NotEmptyNorBlank
    @CharSize(min = "3", max = "16")
    private String password;

    public UserRegistrationDTO() {
    }
    public UserRegistrationDTO(String mail, String fio, String password) {
        this.mail = mail;
        this.fio = fio;
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public String getFio() {
        return fio;
    }

    public String getPassword() {
        return password;
    }

    public static class UserRegistrationBuilder {

        private String mail;
        private String fio;
        private String password;

        public UserRegistrationBuilder() {
        }

        public static UserRegistrationBuilder create() {
            return new UserRegistrationBuilder();
        }


        public UserRegistrationBuilder setFio(String fio) {
            this.fio = fio;
            return this;
        }

        public UserRegistrationBuilder setMail(String mail) {
            this.mail = mail;
            return this;
        }

        public UserRegistrationBuilder setPassword(String password) {
            this.password = password;
            return this;
        }

        public UserRegistrationDTO build() {
            return new UserRegistrationDTO(mail, fio, password);
        }

        @Override
        public String toString() {
            return "AdminUserBuilder{" +
                    "mail='" + mail + '\'' +
                    ", fio='" + fio + '\'' +
                    ", password='" + password + '\'' +
                    '}';
        }
    }

}