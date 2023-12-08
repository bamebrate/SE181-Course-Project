package banking;

public abstract class Account {
    public double balance = 0;
    public double apr;
    public int age = 0;
    private String id;

    public Account(String id, double apr) {
        this.id = id;
        this.apr = apr;
    }

    public abstract String getAccountStatus(Account account);

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

    public int getAge() {
        return age;
    }

    public abstract void passTimeAndCalculateAPR(int month);

    public abstract boolean canTransfer();

    public abstract boolean canWithdraw(double balance, double amount, int age);

    public abstract boolean canDeposit(double amount);
}
