package com.eviro.assessment.grad001.TshepangMaila;

import java.math.BigDecimal;
import java.util.ArrayList;

public class SystemDB{

    private static SystemDB mInstance;
    private static ArrayList<CurrentAccount> data;
    private static ArrayList<SavingsAccount> savingsAccounts;

    private SystemDB(){

    }

    public static SystemDB getInstance(){

        if (SystemDB.mInstance == null){

            SystemDB.mInstance = new SystemDB();

        }

        return SystemDB.mInstance;

    }

    public static ArrayList<CurrentAccount> currentAccountData(){

        SystemDB.data = new ArrayList<>();

        SystemDB.data.add(new CurrentAccount(103,"3",new BigDecimal(1000),
                new BigDecimal(10000)));
        SystemDB.data.add(new CurrentAccount(104,"4",new BigDecimal(-5000),
                new BigDecimal(20000)));

        return data;

    }

    public static ArrayList<SavingsAccount> savingsAccountData(){

        SystemDB.savingsAccounts = new ArrayList<>();

        SystemDB.savingsAccounts.add(new SavingsAccount(101,"1",new BigDecimal(2000)));
        SystemDB.savingsAccounts.add(new SavingsAccount(102,"2",new BigDecimal(5000)));

        return SystemDB.savingsAccounts;

    }

}
