package com.eviro.assessment.grad001.TshepangMaila.exceptions;

import javax.security.auth.login.AccountNotFoundException;

public class UserAccountNotFound extends AccountNotFoundException {

    public UserAccountNotFound() {
    }

    public UserAccountNotFound(String msg) {
        super(msg);

    }

    @Override
    public String getMessage() {

        return super.getMessage();
    }

}