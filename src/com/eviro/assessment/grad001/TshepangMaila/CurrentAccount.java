package com.eviro.assessment.grad001.TshepangMaila;

import com.eviro.assessment.grad001.TshepangMaila.exceptions.UserAccountNotFound;
import com.eviro.assessment.grad001.TshepangMaila.exceptions.WithdrawAmountExceedingAccount;

import java.math.BigDecimal;
import java.util.ArrayList;

public class CurrentAccount implements AccountService {

    //Class Fields
    private int id;
    private String accountNum;
    private BigDecimal balance,overdraft;

    /**
     * Constructor With @params ->
     * @param id Technical ID Associated With A User
     * @param accountNum Account Number Of User
     * @param balance Money In The Account
     * @param overdraft
     * */
    public CurrentAccount(int id, String accountNum, BigDecimal balance, BigDecimal overdraft) {
        this.id = id;
        this.accountNum = accountNum;
        this.balance = balance;
        this.overdraft = overdraft;
    }
    //Non-default constructor used to call the with implemented interface method withdraw()
    public CurrentAccount(){};

    /**
     * @see AccountService
     * */
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
        return accountNum;
    }

    /**
     * @see AccountService
     * */
    @Override
    public BigDecimal getBalance() {
        return balance;
    }

    /**
     * @return overdraft : Returns Overdraft Of The Account
     * */
    public BigDecimal getOverdraft() {
        return overdraft;
    }

    /**
     *  @see AccountService
     *  Implements Abstract Method withdraw From AccountService
     *  @param accountNum : Account Number Associated With A User
     *  @param amountToWithdraw : Amount Of Money To Be Withdrawn By User
     * */
    @Override
    public void withdraw(String accountNum, BigDecimal amountToWithdraw) {

        try {

            /**
             * @see SystemDB
             * Find The Current Account Associated With Account Number Supplied
             * */
            int AccIndex = SystemDB.getInstance().findCurrentAccount(accountNum);

            /* Check If Index Returned Is Positive */
            if (AccIndex >= 0){

                /**
                 * @see SystemDB
                 * CurrentAccount Associated With This Account & Index Number Was Found
                 * */
                CurrentAccount currentAccount = SystemDB.getInstance().getCurrentAccount(AccIndex);

                BigDecimal overdraftLimit = currentAccount.getOverdraft();

                // If Limit Exceeded
                if (amountToWithdraw.compareTo(overdraftLimit.add(currentAccount.getBalance())) >= 0){

                    /**
                     * @see WithdrawAmountExceedingAccount
                     * Throws Custom Exception For Withdrawals Exceeding Balance
                     * */
                    throw  new WithdrawAmountExceedingAccount("Withdrawal Exceeding : You Have Exceeded The Amount You Can Withdraw.");

                }else if (amountToWithdraw.compareTo(overdraftLimit.add(currentAccount.getBalance())) < 0){

                    // Limit Not Exceeded

                    System.out.println("\n\n************* | Withdrawal : R" + amountToWithdraw + " |*************");
                    currentAccount.setBalance((overdraftLimit.add(currentAccount.getBalance()).subtract(amountToWithdraw)));

                    System.out.println("************* | Balance    : R" + currentAccount.getBalance() + " |*************\n\n");

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