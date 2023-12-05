package banking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bank {
    private Map<String, Account> accounts;

    Bank() {
        accounts = new HashMap<>();
    }

    public Map<String, Account> getAccounts() {
        return accounts;
    }

    public void addSavingsAccount(String id, double apr) {
        accounts.put(id, new Savings(id, apr));
    }

    public void addCheckingAccount(String id, double apr) {
        accounts.put(id, new Checking(id, apr));
    }

    public void addCDAccount(String id, double apr, double balance) {
        accounts.put(id, new CD(id, apr, balance));
    }

    public void depositMoneyById(String id, int amount) {
        accounts.get(id).depositMoney(amount);
    }

    public void withdrawMoneyById(String id, int amount) {
        accounts.get(id).withdrawMoney(amount);
    }

    public void passTime(int month) {
        List<String> accountsToClose = new ArrayList<>();

        for (String accountID : accounts.keySet()) {
            Account account = accounts.get(accountID);
            if (account.balance == 0) {
                accountsToClose.add(accountID);
                continue;
            }
            account.passTimeAndCalculateAPR(month);
        }
    }
}
