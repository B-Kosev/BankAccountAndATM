package sample;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

public class ATM {
    String name;
    Account card = null;

    public ATM(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        if (name == null) {
            this.name = "Unknown";
        } else {
            this.name = name;
        }

    }

    public void insertCard(Account card) {
        this.card = card;
    }

    public void ejectCard() {
        this.card = null;
    }

    public void showMenu() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        if (this.card != null) {
            System.out.printf("Welcome %s to %s ATM!", this.card.getName(), this.getName());
            System.out.println();
            Scanner input = new Scanner(System.in);

            Method depositMoney = Account.class.getDeclaredMethod("depositMoney", double.class);
            depositMoney.setAccessible(true);

            Method withdrawMoney = Account.class.getDeclaredMethod("withdrawMoney", double.class);
            withdrawMoney.setAccessible(true);

            while (true) {
                System.out.printf("Please choose one of the options: \n\n");
                System.out.printf("1. Check balance\n");
                System.out.printf("2. Deposit money\n");
                System.out.printf("3. Withdraw money\n");
                System.out.printf("4. Exit \n\n");
                int option = input.nextInt();
                int pin;
                int amount;
                switch (option) {
                    case 1:
                        System.out.printf("Please enter your PIN: ");
                        pin = input.nextInt();
                        if (this.card.validatePin(pin)) {
                            System.out.printf("=======================\n");
                            System.out.printf("You have %f$.\n", this.card.getBalance());
                            System.out.printf("=======================\n");
                        } else {
                            System.out.printf("Incorrect PIN!\n");
                        }
                        break;
                    case 2:
                        System.out.printf("Please enter your PIN: ");
                        pin = input.nextInt();
                        if (this.card.validatePin(pin)) {
                            System.out.printf("How much money do you want to deposit? ");
                            amount = input.nextInt();
                            depositMoney.invoke(card, amount);
                        } else {
                            System.out.printf("Incorrect PIN!\n");
                        }
                        break;
                    case 3:
                        System.out.printf("Please enter your PIN: ");
                        pin = input.nextInt();
                        if (this.card.validatePin(pin)) {
                            System.out.printf("How much money do you want to withdraw? ");
                            amount = input.nextInt();
                            withdrawMoney.invoke(card, amount);
                        } else {
                            System.out.printf("Incorrect PIN!\n");
                        }
                        break;
                    case 4:
                        this.ejectCard();
                        System.exit(0);
                }
            }
        }

        System.out.printf("Please insert card\n");
    }
}