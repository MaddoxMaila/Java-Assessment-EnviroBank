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
     * @return id Returns The Technical ID
     * */
    public int getId() {
        return id;
    }

    /**
     * @param balance Sets The Balance In The Account
     * */
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    /**
     * @return String accountNum: Returns The Account Number Associated With The Account
     * */
    public String getAccountNum() {
        return accountNum;
    }

    /**
     * @return BigDecimal balance : Returns Balance Of The Account
     * */
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
     * @param currentAccountsList List Of Current Accounts
     * @param accountNum Account Number Of Any Account
     * @return int i : Index Of Account Associated With The Account Number Supplied
     * */
    private int findAccount(ArrayList<CurrentAccount> currentAccountsList, String accountNum){

        /* Self Explanatory! */
        if(currentAccountsList.isEmpty()) return -1;

        /* Traverse Through The Accounts */
        for(int i = 0; i < currentAccountsList.size(); i++){

            /* Compare Account Numbers With That Supplied */
            if (currentAccountsList.get(i).getAccountNum().equals(accountNum)){
                return  i; // Returns The Index Of Account
            }

            /* If Loop Reaches The End And Still No Account Is Found */
            if(currentAccountsList.size() - 1 == i){
                return -1;
            }

        }

        return -1;

    }

    /**
     *  @see AccountService
     *  Implements Abstract Method withdraw From AccountService
     *  @param accountNum : Account Number Associated With A User
     *  @param amountToWithdraw : Amount Of Money To Be Withdrawn By User
     * */
    @Override
    public void withdraw(String accountNum, BigDecimal amountToWithdraw) {

        // ArryList Which Will Hold Objects Of CurrentAccount
        ArrayList<CurrentAccount> mCurrentAccounts = SystemDB.getInstance().currentAccountData();

        try {

            /* Find The Account Associated With Account Number Supplied */
            int AccIndex = this.findAccount(mCurrentAccounts, accountNum);

            /* Check If Index Returned Is Positive */
            if (AccIndex > 0){

                /* Account Associated With This Account Number Was Found */

                CurrentAccount currentAccount = mCurrentAccounts.get(AccIndex);

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

                    System.out.println("Withdrawal Success Of R" + amountToWithdraw);
                    currentAccount.setBalance((overdraftLimit.add(currentAccount.getBalance()).subtract(amountToWithdraw)));

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