import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        BankFacade bf = new BankFacade();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("1. Logga in\n2. Registrera nytt konto\n3. Exit\n");
            String choice = scanner.next();

            switch (choice) {
                case "1" -> {
                    if (bf.login()) {
                        bf.userChoice();
                    }
                }
                case "2" -> {
                    bf.register();
                    bf.userChoice();
                }
                case "3" -> System.exit(0);
                default -> System.out.println("Ogiltig inmatning. Försök igen");
            }
        }
    }
}