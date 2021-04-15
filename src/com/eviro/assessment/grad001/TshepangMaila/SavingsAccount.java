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


    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public String getAccountNumber() {
        return accountNum;
    }

    @Override
    public BigDecimal getBalance() {
        return balance;
    }


    //Withdraw method

    @Override
    public void withdraw(String accountNum, BigDecimal amountToWithdraw) {

        //ArryList Which Will Hold Objects Of CurrentAccount
        ArrayList<SavingsAccount> accounts = SystemDB.getInstance().savingsAccountData();

        //Try and Catch Block To Handle The Account Not Found Error At Runtime

        try {

            //Boolen variable to check if the account number is found the objects of array
            boolean accFound = false;
            //Index to used to access the current account in set of CurrentAccount Objects(ArrayList)
            int accIndex = -1;

            //For loop to traverse
            for (int i = 0; i < accounts.size(); i++){

                //if account number found
                if (accounts.get(i).getAccountNumber().equals(accountNum)){

                    accFound = true;
                    accIndex = i;
                    break;

                }
                //if reached end of loop and account not found
                if (i == accounts.size() - 1 && !accFound){

//                    Object accountNotFoundException = new Exception("Account Not Found");
//
//                    throw (Throwable) accountNotFoundException;

                    throw new UserAccountNotFound("Account Not Found");

                }

            }

            //if account found
            if (accFound){

                SavingsAccount savingsAccount = accounts.get(accIndex);


                if ((savingsAccount.getBalance().subtract(amountToWithdraw)).compareTo(new BigDecimal(1000)) < 0){

                    //System.out.println("You Cannot Withdraw The Requested Amount");
                    throw new WithdrawAmountExceedingAccount("You Cannot Withdraw The Requested Amount");

                }else if ((savingsAccount.getBalance().subtract(amountToWithdraw)).compareTo(new BigDecimal(1000)) >= 0){

                    System.out.println("Withdraw Success Amount : R" + amountToWithdraw);
                    savingsAccount.setBalance(savingsAccount.getBalance().subtract(amountToWithdraw));

                }

            }



        } catch (Throwable throwable) {

            System.out.println(throwable.getMessage());

        }

    }
}