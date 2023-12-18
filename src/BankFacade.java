import java.util.Scanner;

public class BankFacade {

    private ContactsFromFile contactsFromFile;
    private BankingSystem banksystem;
    private Account account;
    private UserInterface ui;

    public Account getAccount() {
        return account;
    }

    public BankFacade() {
        contactsFromFile = ContactsFromFile.getInstance();
        banksystem = new BankingSystem();
        account = new Account();
        ui = new UserInterface(new Scanner(System.in), account, this);

    }

    public void register() {
        banksystem.registerMember();
    }

    public boolean login() {
        return banksystem.logIn();

    }

    public void save(String personNumber, String customerName) {
        banksystem.saveMember(personNumber, customerName);
    }

    public boolean authenticate(String personNumber, String customerName) {
        return banksystem.authenticateMember(personNumber, customerName);
    }

    public void contacts() {
        contactsFromFile.getContacts();
    }

    public void deposit(double amount) {
        account.deposit(amount);
    }

    public void withdraw(double amount) {
        account.withdraw(amount);
    }

    public void showBalance() {
        account.showBalance();
    }

    public void blockAccount() {
        account.blockAccount();
    }

    public void pay(double amount) {
        account.pay(amount);
    }

    public void loan(double amount) {
        account.loan(amount);
    }

    public void activateOnlinePurchases() {
        ui.activateOnlinePurchases();
    }

    public boolean userChoice() throws InterruptedException {
        return ui.userChoice();
    }

}
