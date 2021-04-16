package com.eviro.assessment.grad001.TshepangMaila;

import java.math.BigDecimal;

/**
 * @author Tshepang Maddox Maila
 * @since 15 April 2021
 *
 * */

public interface AccountService {

    /**
     * @param accountNum : Account Number Associated With A User
     * @param amountToWithdraw : Amount Of Money To Be Withdrawn By User
     *
     * */
    public void withdraw(String accountNum, BigDecimal amountToWithdraw);

    /**
     * @param balance Sets The Balance In The Account
     * */
    public void setBalance(BigDecimal balance);

    /**
     * @return id Returns The Technical ID
     * */
    public int getId();

    /**
     * @return String accountNum: Returns The Account Number Associated With The Account
     * */
    public String getAccountNumber();

    /**
     * @return BigDecimal balance : Returns Balance Of The Account
     * */
    public BigDecimal getBalance();

}
