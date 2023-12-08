package banking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bank {
    private Map<String, Account> accounts;
    private List<String> accountList;

    Bank() {
        accounts = new HashMap<>();
        accountList = new ArrayList<>();
    }

    public Map<String, Account> getAccounts() {
        return accounts;
    }

    public void addSavingsAccount(String id, double apr) {
        accounts.put(id, new Savings(id, apr));
        accountList.add(id);
    }

    public void addCheckingAccount(String id, double apr) {
        accounts.put(id, new Checking(id, apr));
        accountList.add(id);
    }

    public void addCDAccount(String id, double apr, double balance) {
        accounts.put(id, new CD(id, apr, balance));
        accountList.add(id);
    }

    public void depositMoneyById(String id, double amount) {
        accounts.get(id).depositMoney(amount);
    }

    public void withdrawMoneyById(String id, double amount) {
        accounts.get(id).withdrawMoney(amount);
    }

    public void transferMoneyById(String fromID, String toID, double amount) {
        accounts.get(fromID).withdrawMoney(amount);
        accounts.get(toID).depositMoney(amount);
    }

    public void passTime(int month) {
        List<String> accountsToClose = new ArrayList<>();

        for (String accountID : accounts.keySet()) {
            Account account = accounts.get(accountID);
            if (account.balance == 0) {
                accountsToClose.add(accountID);
                continue;
            } else {
                if (account.balance < 100) {
                    accounts.get(accountID).withdrawMoney(25);
                }
                account.passTimeAndCalculateAPR(month);
            }
        }


        for (String ID : accountsToClose) {
            accounts.remove(ID);
            accountList.remove(ID);
        }
    }

    public List<String> getAccountList() {
        return accountList;
    }

}

