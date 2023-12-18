public class Account {
    
    private String accountNumber;
    private String accountName;
    private double balance;
    String setTextYellow = "\u001B[33m";
    String turnOffTextYellow = "\u001B[0m";

    public Account() {
    }

    public Account(String accountNumber, String accountName) {
        this.accountNumber = accountNumber;
        this.accountName = accountName;
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getAccountName() {
        return accountName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public void deposit(double amount) {
        balance += amount;
        System.out.println(amount + " kronor sattes in på ditt konto");
    }

    public void withdraw(double amount) {
        if (balance >= amount) {
            balance = (balance - amount);
            System.out.println(amount + " kronor togs ut från ditt konto");
        } else {
            System.out.println("Du har inte tillräckligt med pengar på kontot");
        }
    }

    public void showBalance() {
        System.out.println("Ditt saldo är: " + setTextYellow + balance + turnOffTextYellow);
    }

    public void pay(double amount) {
        if (balance >= amount) {
            balance = (balance - amount);
            System.out.println("Betalning på " + amount + "kr" + " registrerades");
        } else {
            System.out.println("Du har inte tillräckligt med pengar på kontot");
        }

    }

    public void loan(double amount) {
        balance += amount;
        System.out.println(amount + "kr sattes in på konto: " + getAccountName());
    }

    public void blockAccount() {
        System.out.println("Kontot är spärrat!");


    }

    public void activateOnlinePurchases() {
        System.out.println("Online köp är aktiverat");
    }


}
