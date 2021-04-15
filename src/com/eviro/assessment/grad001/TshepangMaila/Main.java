package com.eviro.assessment.grad001.TshepangMaila;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        try{


            Scanner kb = new Scanner(System.in);

            System.out.println("**************MAIN MENU***************");
            System.out.println("1. Withdraw");
            int option = -1;
            option = kb.nextInt();

            while (option != -1){

                if (option != 1) {

                    System.out.println("Wrong Input. Please Key In Again Correct Value.");
                    option = kb.nextInt();

                }else{

                    System.out.println("1.Saving Account :\n2.Current Account :");
                    int accType = kb.nextInt();

                    System.out.print("Enter Account Number : ");
                    kb.nextLine();
                    String accNumber = kb.nextLine();
                    System.out.print("Enter Amount To Withdraw : ");
                    int amountToWithdraw = kb.nextInt();

                    switch(accType){

                        case 1 :

                            SavingsAccount savingsAccount = new SavingsAccount();

                            savingsAccount.withdraw(accNumber, new BigDecimal(amountToWithdraw));


                            break;

                        case 2 :

                            CurrentAccount currentAccount = new CurrentAccount();

                            currentAccount.withdraw(accNumber, new BigDecimal(amountToWithdraw));

                            break;


                            default:

                                System.out.println("\n--- Either Choose Savings or Current Account ---\n\n");

                                break;

                    }

                }

            }

        }catch (InputMismatchException e){
            System.out.println(e.toString());
        }

    }

}