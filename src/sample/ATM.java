package sample;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

public class ATM {
    String name;
    Account card=null;

    public ATM(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null) {
            this.name = "Unknown";
        } else {
            this.name = name;
        }
    }

    public void insertCard(final Account card) {
        this.card = card;
    }

    public void ejectCard() {
        this.card = null;
    }

    public void showMenu() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        if(card==null){
            System.out.printf("Please insert card\n");
            return;
        }

        Method depositMoney=Account.class.getDeclaredMethod("depositMoney",double.class);
        Method withdrawMoney=Account.class.getDeclaredMethod("withdrawMoney", double.class);
        depositMoney.setAccessible(true);
        withdrawMoney.setAccessible(true);


        System.out.printf("Welcome %s to %s ATM!", card.getName(), this.getName());
        System.out.println();

        int option;
        int pin;
        Scanner input = new Scanner(System.in);
        while(true) {
            System.out.printf("Please choose one of the options: \n\n");
            System.out.printf("1. Check balance\n");
            System.out.printf("2. Deposit money\n");
            System.out.printf("3. Withdraw money\n");
            System.out.printf("4. Exit \n\n");
            option = input.nextInt();

            switch (option) {
                case 1:
                    System.out.printf("Please enter your PIN: ");
                    pin = input.nextInt();
                    if (card.validatePin(pin)) {
                        System.out.printf("=======================\n");
                        System.out.printf("You have %f$.\n", card.getBalance());
                        System.out.printf("=======================\n");
                    }
                    else{
                        System.out.printf("Incorrect PIN!\n");
                        continue;
                    }
                    break;
                case 2:
                    System.out.printf("Please enter your PIN: ");
                    pin = input.nextInt();
                    if (card.validatePin(pin)) {
                        System.out.printf("How much money do you want to deposit? ");
                        int amount=input.nextInt();
                        depositMoney.invoke(card,amount);
                    }
                    else{
                        System.out.printf("Incorrect PIN!\n");
                        continue;
                    }
                    break;
                case 3:
                    System.out.printf("Please enter your PIN: ");
                    pin = input.nextInt();
                    if (card.validatePin(pin)) {
                        System.out.printf("How much money do you want to withdraw? ");
                        int amount=input.nextInt();
                        //card.withdrawMoney(amount);
                        withdrawMoney.invoke(card,amount);
                    }
                    else{
                        System.out.printf("Incorrect PIN!\n");
                        continue;
                    }
                    break;
                case 4:
                    ejectCard();
                    System.exit(0);
            }
        }
    }
}
