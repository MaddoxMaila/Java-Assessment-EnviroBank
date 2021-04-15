package com.eviro.assessment.grad001.TshepangMaila;

import java.math.BigDecimal;
import java.util.ArrayList;


public class SystemDB{

    private static SystemDB mInstance;
    private ArrayList<CurrentAccount> currentAccounts;
    private ArrayList<SavingsAccount> savingsAccounts;

    /**
     * Constructor
     * Just used to Initialize the Database
     * */
    private SystemDB(){

        /* Initialize Dummy Current Accounts */
        this.currentAccounts = new ArrayList<>();
        this.currentAccounts.add(new CurrentAccount(103,"3",new BigDecimal(1000),
                new BigDecimal(10000)));
        this.currentAccounts.add(new CurrentAccount(104,"4",new BigDecimal(-5000),
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
     * @return ArrayList<CurrentAccount> currentAccounts
     * Holds All CurrentAccounts In The Database
     * */
    public ArrayList<CurrentAccount> currentAccountData(){
        return this.currentAccounts;
    }

    /**
     * @return ArrayList<CurrentAccount> currentAccounts
     * Holds All SavingsAccounts In The Database
     * */
    public ArrayList<SavingsAccount> savingsAccountData(){
        return this.savingsAccounts;
    }

    /**
     * @param index of SavingsAccount To Be Returned
     * @return SavingsAccount
     * */
    public SavingsAccount getSavingsAccount(int index){
        return this.savingsAccounts.get(index);
    }

    /**
     * @param index of CurrentAccount To Be Returned
     * @return CurrentAccount
     * */
    public CurrentAccount getCurrentAccount(int index){
        return this.currentAccounts.get(index);
    }

    /**
     * @param accountNum Account Number Of Any Account
     * @return int i : Index Of Savings Account Associated With The Account Number Supplied
     * */
    public int findSavingsAccount(String accountNum){

        /* Self Explanatory! */
        if(this.savingsAccounts.isEmpty()) return -1;

        /* Traverse Through The Accounts */
        for(int i = 0; i < this.savingsAccounts.size(); i++){

            /* Compare Savings Account Numbers With That Supplied */
            if (this.savingsAccounts.get(i).getAccountNumber().equals(accountNum)){
                return  i; // Returns The Index Of Account
            }

            /* If Loop Reaches The End And Still No Account Is Found */
            if(this.savingsAccounts.size() - 1 == i){
                return -1;
            }

        }

        return -1;

    }
    /**
     * @param accountNum Account Number Of Any Account
     * @return int i : Index Of Account Associated With The Account Number Supplied
     * */
    public int findCurrentAccount(String accountNum){

        /* Self Explanatory! */
        if(this.currentAccounts.isEmpty()) return -1;

        /* Traverse Through The Accounts */
        for(int i = 0; i < this.currentAccounts.size(); i++){

            /* Compare Account Numbers With That Supplied */
            if (this.currentAccounts.get(i).getAccountNumber().equals(accountNum)){
                return  i; // Returns The Index Of Account
            }

            /* If Loop Reaches The End And Still No Account Is Found */
            if(this.currentAccounts.size() - 1 == i){
                return -1;
            }

        }

        return -1;

    }

}
