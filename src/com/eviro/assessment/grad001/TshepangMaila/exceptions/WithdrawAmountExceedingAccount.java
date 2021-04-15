package com.eviro.assessment.grad001.TshepangMaila.exceptions;

public class WithdrawAmountExceedingAccount  extends Exception{

    public WithdrawAmountExceedingAccount(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}