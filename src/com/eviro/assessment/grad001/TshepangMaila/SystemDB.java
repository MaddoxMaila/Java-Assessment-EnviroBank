package com.eviro.assessment.grad001.TshepangMaila;

import java.math.BigDecimal;
import java.util.ArrayList;


public class SystemDB{

    private static SystemDB mInstance;
    private ArrayList<CurrentAccount> data;
    private ArrayList<SavingsAccount> savingsAccounts;

    /**
     * Constructor
     * Just used to Initialize the Database
     * */
    private SystemDB(){

        /* Initialize Dummy Current Accounts */
        this.data = new ArrayList<>();
        this.data.add(new CurrentAccount(103,"3",new BigDecimal(1000),
                new BigDecimal(10000)));
        this.data.add(new CurrentAccount(104,"4",new BigDecimal(-5000),
                new BigDecimal(20000)));

        /* Initialize Dummy Saving Accounts */
        this.savingsAccounts = new ArrayList<>();
        this.savingsAccounts.add(new SavingsAccount(101,"1",new BigDecimal(2000)));
        this.savingsAccounts.add(new SavingsAccount(102,"2",new BigDecimal(5000)));

    }

    /**
     * Implement The Singleton Pattern
     * Makes Sure That Only One Object Of This Class Will Exist In The Entire Lifetime Of Its Runtime
     * @return SystemDB
     *
     * */
    public static SystemDB getInstance(){

        // Checks If mInstance Has Already Been Initialized
        if (SystemDB.mInstance == null){

            // If Not Initialized, Creates A New Object
            SystemDB.mInstance = new SystemDB();

        }

        // Returns The Instance Object Either With The Newly Created Object or The Old One Created At The Start
        return SystemDB.mInstance;

    }

    /**
     * @return ArrayList<CurrentAccount> data
     * Holds All CurrentAccounts In The Database
     * */
    public ArrayList<CurrentAccount> currentAccountData(){
        return this.data;
    }

    /**
     * @return ArrayList<CurrentAccount> data
     * Holds All SavingsAccounts In The Database
     * */
    public ArrayList<SavingsAccount> savingsAccountData(){
        return this.savingsAccounts;
    }

}
