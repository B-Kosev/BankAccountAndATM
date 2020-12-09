package sample;

public class Account {
    private String name;
    private int ID;
    private double balance;
    private int pin;
    private static int idCounter = 1000;

    public Account(String name,int pin) {
        setName(name);
        setID();
        setPin(pin);
        this.balance=0.0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name==null){
            this.name="Unknown";
        }
        else {
            this.name=name;
        }
    }

    public int getID() {
        return ID;
    }

    public void setID() {
        this.ID = idCounter++;
    }

    public double getBalance() {
        return balance;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public boolean validatePin(int pin){
        return this.pin==pin?true:false;
    }

    private void depositMoney(double amount){
        if(amount>0) {
            this.balance += amount;
            System.out.printf("Successfully deposited %.2f$.\n",amount);
        }
    }

    private void withdrawMoney(double amount){
        if(amount>this.balance) {
            System.out.printf("You don't have enough money.\n");
        }
        else if(amount<balance && amount>0){
            this.balance-=amount;
            System.out.printf("Successfully withdrawn %.2f$.\n",amount);
        }
    }
}
