import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class BankingSystem {
    private final String memberFile = "src/Konton.txt";
    private final String logInBirth = "Personnummer: ";
    private final String logInName = "Namn: ";

    public String getLogInBirth() {
        return logInBirth;
    }

    public String getLogInName() {
        return logInName;
    }

    public void registerMember() {
        Scanner scan = new Scanner(java.lang.System.in);

        java.lang.System.out.println(logInBirth);
        String personNumber = scan.nextLine();

        java.lang.System.out.println(logInName);
        String customerName = scan.nextLine();

        saveMember(personNumber, customerName);

    }

    public void saveMember(String personNumber, String customerName) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(memberFile, true))) {
            bw.write(personNumber + "\n");
            bw.write(customerName + "\n");
            java.lang.System.out.println("Konto skapat");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean logIn() {
        Scanner scan = new Scanner(java.lang.System.in);

        java.lang.System.out.println(logInBirth);
        String personNumber = scan.nextLine();

        java.lang.System.out.println(logInName);
        String customerName = scan.nextLine();

        return authenticateMember(personNumber, customerName);
    }

    public boolean authenticateMember(String personNumber, String customerName) {
        try (Scanner fileScanner = new Scanner(new java.io.File(memberFile))) {
            boolean found = false;

            while (fileScanner.hasNextLine()) {
                String storedPersonNumber = fileScanner.nextLine();
                String storedName = fileScanner.nextLine();

                if (storedPersonNumber.equals(personNumber) && storedName.equals(customerName)) {
                    found = true;
                    break;
                }
            }
            if (found) {
                java.lang.System.out.println("Inloggad");
                return true;
            }
            java.lang.System.out.println("Felaktig inmatning av personummer eller namn");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}


