package com.lc.tacos.domain;

import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author DELL
 * @date 2022/4/23 19:24
 */
@Data
public class RegistrationForm {

    private final String username;
    private final String password;
    private final String fullname;
    private final String street;
    private final String city;
    private final String state;
    private final String zip;
    private String phonenumber;

    public User toUser(PasswordEncoder passwordEncoder) {
        return new User(
                username, passwordEncoder.encode(password),
                fullname, street, city, state, zip, phonenumber);
    }
}
