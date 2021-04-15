package com.eviro.assessment.grad001.TshepangMaila;

import java.math.BigDecimal;

/**
 * AccountService Interface includes one method to be implemented By
 * CurrentAccount & SavingsAccount classes
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
}
