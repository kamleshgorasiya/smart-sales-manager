package com.smart.sales.manager.request.model;



import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.smart.sales.manager.constraint.FieldMatch;

@FieldMatch(first = "password", second = "confirmPassword", message = "The password fields must match")
public class PasswordResetDto {

    @NotEmpty
    @Size(min = 8, max = 32, message="Password must not be less than 8 and more than 32 character long")
    private String password;

    @NotEmpty
    @Size(min = 8, max = 32, message="Password must not be less than 8 and more than 32 character long")
    private String confirmPassword;

    @NotEmpty
    private String token;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
