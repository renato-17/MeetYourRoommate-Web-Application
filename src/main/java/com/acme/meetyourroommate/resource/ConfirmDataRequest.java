package com.acme.meetyourroommate.resource;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ConfirmDataRequest {
    @NotNull
    @NotBlank
    private String mail;
    @NotNull
    @NotBlank
    private String password;

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
