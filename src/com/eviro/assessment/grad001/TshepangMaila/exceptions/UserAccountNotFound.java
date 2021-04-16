package com.eviro.assessment.grad001.TshepangMaila.exceptions;

import javax.security.auth.login.AccountNotFoundException;

/**
 * @author Tshepang Maddox Maila
 * @since 15 April 2021
 *
 * */


public class UserAccountNotFound extends AccountNotFoundException {

    public UserAccountNotFound() {
    }

    public UserAccountNotFound(String msg) {
        super("\n\n************************************\n - \t\t\t" + msg + "\n************************************\n");

    }

    @Override
    public String getMessage() {

        return super.getMessage();
    }

}