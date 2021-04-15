package com.eviro.assessment.grad001.TshepangMaila;

import com.eviro.assessment.grad001.TshepangMaila.exceptions.UserAccountNotFound;
import com.eviro.assessment.grad001.TshepangMaila.exceptions.WithdrawAmountExceedingAccount;

import javax.security.auth.login.AccountNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;

public class CurrentAccount implements AccountService {

    //Class Fields
    private int id;
    private String accountNum;
    private BigDecimal balance,overdraft;

    //Constructor to populate the object db
    public CurrentAccount(int id, String accountNum, BigDecimal balance, BigDecimal overdraft) {
        this.id = id;
        this.accountNum = accountNum;
        this.balance = balance;
        this.overdraft = overdraft;
    }
    //Non-default constructor used to call the with implemented interface method withdraw()
    public CurrentAccount(){};

    //Mutators And Accessors Methods
    public int getId() {
        return id;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getAccountNum() {
        return accountNum;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public BigDecimal getOverdraft() {
        return overdraft;
    }

    //Withdraw method

    @Override
    public void withdraw(String accountNum, BigDecimal amountToWithdraw) {

        //ArryList Which Will Hold Objects Of CurrentAccount
        ArrayList<CurrentAccount> accounts = SystemDB.currentAccountData();

        //Try and Catch Block To Handle The Account Not Found Error At Runtime

        try {

            //Boolen variable to check if the account number is found the objects of array
            boolean accFound = false;
            //Index to used to access the current account in set of CurrentAccount Objects(ArrayList)
            int accIndex = -1;

            //For loop to traverse
            for (int i = 0; i < accounts.size(); i++){

                //if account number found
                if (accounts.get(i).getAccountNum().equals(accountNum)){

                    accFound = true;
                    accIndex = i;
                    break;

                }
                //if reached end of loop and account not found
                if (i == accounts.size() - 1 && !accFound){

                    //Object accountNotFoundException = new Exception("Account Not Found");

                    throw new UserAccountNotFound("Account Not Found");


                }

            }

            //if account found
            if (accFound){

                CurrentAccount currentAccount = accounts.get(accIndex);

                BigDecimal overdraftLimit = currentAccount.getOverdraft();

                //if limit exceeded
                if (amountToWithdraw.compareTo(overdraftLimit.add(currentAccount.getBalance())) >= 0){

//                    System.out.println("You have exceeded your balance and overdraft limit.");

                    throw  new WithdrawAmountExceedingAccount("You have exceeded your balance and overdraft limit.");


                }else if (amountToWithdraw.compareTo(overdraftLimit.add(currentAccount.getBalance())) < 0){ // limit not exceeded

                    System.out.println("Withdrawal Success Of R" + amountToWithdraw);
                    currentAccount.setBalance((overdraftLimit.add(currentAccount.getBalance()).subtract(amountToWithdraw)));

                }

            }



        } catch (Throwable throwable) {

            System.out.println(throwable.getMessage());

        }

    }
}