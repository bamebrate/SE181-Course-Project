package banking;

public abstract class Account {
    public double balance = 0;
    private String id;
    private double apr;
    private int age = 0;

    public Account(String id, double apr) {
        this.id = id;
        this.apr = apr;
    }

    public String getId() {
        return id;
    }

    public double getAPR() {
        return apr;
    }

    public double getBalance() {
        return balance;
    }

    public void depositMoney(double amount) {
        balance += amount;
    }

    public void withdrawMoney(double amount) {
        if (balance > amount) {
            balance -= amount;
        } else {
            balance = 0;
        }
    }

    public abstract String getType();

    public int getAge() {
        return age;
    }

    public void passTimeAndCalculateAPR(int month) {
        age += month;
        for (int monthLoopIndex = 0; monthLoopIndex < month; monthLoopIndex++) {
            balance += ((apr / 100) / 12) * balance;
        }
    }
}
