package thread.work3;

//Создайте класс BankAccount, у которого есть баланс balance. Создайте два потока,
// один из которых пытается снять средства со счета, а другой пытается пополнить счет.
// Используйте синхронизированные блоки, чтобы гарантировать корректное обновление баланса.
public class BankAccount {
    volatile int balance;

    public BankAccount() {
    }

    public BankAccount(int balance) {
        this.balance = balance;
    }
    public void deposit(int amount) {
        synchronized (this) {
            balance = balance + amount;
            System.out.println("Deposit " + amount + ", New balances: " + balance);
        }
    }

    public void withdraw(int amount) {
        synchronized (this) {
            if (balance >= amount) {
                balance = balance - amount;
                System.out.println("Withdraw: " + amount + ", New balances: " + balance);
            } else {
                System.out.println("Insufficient funds! " + amount);
            }
        }
    }


    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}


class BalanceDeposit extends Thread {
    private BankAccount bankAccount;
    public BalanceDeposit(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            int amount = (int) (Math.random() * 20) + 1;
            bankAccount.deposit(amount);
        }
    }
}


class BalanceWithdraw extends Thread {
    private BankAccount bankAccount;

    public BalanceWithdraw(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            int amount = (int) (Math.random() * 40) + 1;
            bankAccount.withdraw(amount);
        }
    }
}

class MainBankAccount {


    public static void main(String[] args) {
        BankAccount bankAccount = new BankAccount();
        BalanceDeposit balanceDeposit = new BalanceDeposit(bankAccount);
        BalanceWithdraw balanceWithdraw = new BalanceWithdraw(bankAccount);

        balanceDeposit.start();
        balanceWithdraw.start();
    }
}