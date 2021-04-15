package com.eviro.assessment.grad001.TshepangMaila.exceptions;

public class WithdrawAmountExceedingAccount  extends Exception{

    public WithdrawAmountExceedingAccount(String message) {
        super("\n************************************\n - \t\t\t" + message + " - \n************************************\n ");
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}