import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UserInterface extends Account {
    private final ContactsFromFile c = ContactsFromFile.getInstance();
    private Scanner scanner;
    private Account account;
    private BankFacade bf;
    private ArrayList<Account> accountList = new ArrayList<>();

    public UserInterface(Scanner scanner, Account account, BankFacade bf) {
        this.scanner = scanner;
        this.account = account;
        this.bf = bf;
    }

    public void showMenu() {
        System.out.println("1. Skapa konto\n2. Visa konton\n3. Insättning\n4. Uttag\n5. Ny betalning\n6. Hantera lån\n7. Spärra konto\n8. Aktivera online-köp\n9. Kontakta oss\n10. Logga ut");
    }

    public boolean userChoice() throws InterruptedException {
        while (true) {
            showMenu();
            int userChoice = getUserChoice();

            switch (userChoice) {
                case 1 -> createAccount();
                case 2 -> showAccounts();
                case 3 -> deposit();
                case 4 -> withdraw();
                case 5 -> payment();
                case 6 -> handleLoan();
                case 7 -> suspendCard();
                case 8 -> activateOnlinePurchases();
                case 9 -> contactUs();
                case 10 -> {
                    return logOut();
                }
            }
        }
    }

    public int getUserChoice() {
        try {
            System.out.print("Välj en handling: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            return choice;
        } catch (InputMismatchException e) {
            System.out.println("Ogiltig inmatning. Vänligen ange ett heltal.");
            scanner.nextLine();
            return -1;
        }
    }

    public void createAccount() {
        System.out.print("Kontonummer: ");
        String accountNumber = scanner.nextLine();
        System.out.print("Namn på konto: ");
        String accountName = scanner.nextLine();
        Account account = new Account(accountNumber, accountName);
        accountList.add(account);
        System.out.println("Nytt konto skapat.");
    }

    public void showAccounts() {
        if (accountList.isEmpty()) {
            System.out.println("Du har inga konton registrerade.");
        } else {
            System.out.println("Konton: ");
            for (Account a : accountList) {
                System.out.println(a.getAccountName() + " " + setTextYellow + a.getBalance() + turnOffTextYellow);
            }
        }
    }

    public void deposit() {
        if (accountList.isEmpty()) {
            System.out.println("Det finns inga konton registrerade.");
        } else {
            System.out.println("Namn på konto för insättning: ");
            for (Account a : accountList) {
                System.out.println("- " + a.getAccountName());
            }
            String choice = scanner.nextLine();
            for (Account a : accountList) {
                if (a.getAccountName().equals(choice)) {
                    System.out.println("Belopp att sätta in: ");
                    double amount = scanner.nextDouble();
                    if (amount >= 0) {
                        double newBalance = a.getBalance() + amount;
                        a.setBalance(newBalance);
                        System.out.println("Insättning genomförd\n" + choice + ": " + setTextYellow + a.getBalance() + turnOffTextYellow);
                        break;
                    } else {
                        System.out.println("Ogiltigt belopp. Sätt in ett positivt belopp");
                    }
                }
            }
        }
    }

    public void withdraw() {
        if (accountList.isEmpty()) {
            System.out.println("Det finns inga konton registrerade.");
        } else {
            System.out.println("Namn på konto för uttag: ");
            for (Account a : accountList) {
                System.out.println("- " + a.getAccountName());
            }
            String choice = scanner.nextLine();
            for (Account a : accountList) {
                if (a.getAccountName().equals(choice)) {
                    System.out.println("Belopp att ta ut: ");
                    double amount = scanner.nextDouble();

                    if (amount > a.getBalance()) {
                        System.out.println("Ej tillräckligt med saldo på kontot.");
                    } else {
                        double newBalance = a.getBalance() - amount;
                        a.setBalance(newBalance);
                        System.out.println("Uttag genomfört\n" + choice + ": " + setTextYellow + a.getBalance() + turnOffTextYellow);
                        break;
                    }
                }
            }
        }
    }

    public void contactUs() {
        c.getContacts();
    }

    public boolean logOut() throws InterruptedException {
        System.out.println("Du är utloggad");
        Thread.sleep(1000);
        System.out.println("Välkommen åter");
        return true;
    }

    public void handleLoan() throws InterruptedException {
        System.out.println("Välj typ av lån\n" +
                "1. Bolån\n" +
                "2. privatlån");
        String typeOfLoan = scanner.nextLine();

        if (typeOfLoan.equals("1")) {
            handleLoanDemand();
        } else if (typeOfLoan.equals("2")) {
            handleLoanDemand();
        }
    }

    public void suspendCard() throws InterruptedException {
        System.out.println("1 för att spärra 2 för att avbryta");
        String choice = scanner.nextLine();
        if (choice.equals("1")) {
            bf.blockAccount();
            removeAccount();
        } else {
            System.out.println("Du avbröt");
        }
        Thread.sleep(1500);
    }

    public void payment() {
        System.out.print("OCR/Kommentar: ");
        String ocrKommentar = scanner.nextLine();
        if (accountList.isEmpty()) {
            System.out.println("Det finns inga konton registrerade");
        } else {
            System.out.println("Namn på konto för betalning: ");
            for (Account a : accountList) {
                System.out.println("- " + a.getAccountName());
            }
            String choice = scanner.nextLine();
            for (Account a : accountList) {
                if (a.getAccountName().equals(choice)) {
                    System.out.println("Belopp att överföra: ");
                    double amount = scanner.nextDouble();

                    if (amount > a.getBalance()) {
                        System.out.println("Ej tillräckligt med saldo på kontot");
                    } else {
                        double newBalance = a.getBalance() - amount;
                        a.setBalance(newBalance);
                        System.out.println("Överföring genomfört\n" + setTextYellow + amount + turnOffTextYellow + " sattes in på konto med OCR " + ocrKommentar);
                        break;
                    }
                }
            }
        }
    }

    public void removeAccount() {
        accountList.clear();
    }

    public void activateOnlinePurchases() {
        account.activateOnlinePurchases();
    }

    public void handleLoanDemand() {
        if (accountList.isEmpty()) {
            System.out.println("Det finns inga konton registrerade.");
        } else {
            System.out.println("Namn på konto för insättning av lån: ");
            for (Account a : accountList) {
                System.out.println("- " + a.getAccountName());
            }
            String choice = scanner.nextLine();
            for (Account a : accountList) {
                if (a.getAccountName().equals(choice)) {
                    System.out.println("Hur mycket vill du låna? ");
                    double amount = scanner.nextDouble();
                    if (amount >= 0) {
                        double newBalance = a.getBalance() + amount;
                        a.setBalance(newBalance);
                        System.out.println("Lån beviljat\n" + choice + ": " + setTextYellow + a.getBalance() + turnOffTextYellow);
                        break;
                    } else {
                        System.out.println("Ogiltigt belopp. Endast positiva belopp");
                    }
                }
            }
        }
    }
}
