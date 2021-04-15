package com.eviro.assessment.grad001.TshepangMaila;

import com.eviro.assessment.grad001.TshepangMaila.exceptions.UserAccountNotFound;
import com.eviro.assessment.grad001.TshepangMaila.exceptions.WithdrawAmountExceedingAccount;


import java.math.BigDecimal;
import java.util.ArrayList;


public class SavingsAccount implements AccountService {

    private int id;
    private String accountNum;
    private BigDecimal balance;

    /**
     * Constructor With @params ->
     * @param id Technical ID Associated With A User
     * @param accountNum Account Number Of User
     * @param balance Money In The Account
     * */
    public SavingsAccount(int id, String accountNum, BigDecimal balance) {
        this.id = id;
        this.accountNum = accountNum;
        this.balance = balance;
    }

    public SavingsAccount(){};


    /**
     * @see AccountService
     * */
    @Override
    public int getId() {
        return id;
    }

    /**
     * @see AccountService
     * */
    @Override
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    /**
     * @see AccountService
     * */
    @Override
    public String getAccountNumber() {
        return this.accountNum;
    }

    /**
     * @see AccountService
     * */
    @Override
    public BigDecimal getBalance() {
        return this.balance;
    }

    /**
     * @see AccountService
     * */
    @Override
    public void withdraw(String accountNum, BigDecimal amountToWithdraw) {

        //Try and Catch Block To Handle The Account Not Found Error At Runtime

        try {

            /**
             * @see SystemDB
             * */
            int AccIndex = SystemDB.getInstance().findSavingsAccount(accountNum);

            // If Account Found
            if (AccIndex >= 0){

                /**
                 * @see SystemDB
                 * SavingsAccount Associated With This Account Number & Index Was Found
                 * */
                SavingsAccount savingsAccount = SystemDB.getInstance().getSavingsAccount(AccIndex);
                
                if ((savingsAccount.getBalance().subtract(amountToWithdraw)).compareTo(new BigDecimal(1000)) < 0){
                    
                    /**
                     * @see WithdrawAmountExceedingAccount
                     * Throw Custom Created Exception
                     * */
                    throw new WithdrawAmountExceedingAccount("You Cannot Withdraw The Requested Amount");

                }else if ((savingsAccount.getBalance().subtract(amountToWithdraw)).compareTo(new BigDecimal(1000)) >= 0){

                    System.out.println("\n\n************* | Withdrawal : R" + amountToWithdraw + " |*************");
                    savingsAccount.setBalance(savingsAccount.getBalance().subtract(amountToWithdraw));

                    System.out.println("************* | Balance    : R" + savingsAccount.getBalance() + " |*************\n\b");

                }

            }else{

                /**
                 * @see UserAccountNotFound
                 * Throws Custom Exception For An Account Thats Not In The DB
                 * */
                throw new UserAccountNotFound("User Account Not Found : This Account Number Does Not Exist");


            }
            
        } catch (Throwable throwable) {

            System.out.println(throwable.getMessage());

        }

    }
}