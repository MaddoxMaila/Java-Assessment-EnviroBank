package com.eviro.assessment.grad001.TshepangMaila.exceptions;

/**
 * AccountService Interface includes one method to be implemented By
 * CurrentAccount & SavingsAccount classes
 * @author Tshepang Maddox Maila
 * @since 15 April 2021
 *
 * */

public class WithdrawAmountExceedingAccount  extends Exception{

    public WithdrawAmountExceedingAccount(String message) {
        super("\n************************************\n - \t\t\t" + message + " - \n************************************\n ");
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}