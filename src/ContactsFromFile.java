import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ContactsFromFile {
    private static ContactsFromFile instance = null;
    private final String contactFile = "src/contacts.txt";
    private final String setTextYellow = "\u001B[33m";
    private final String turnOffTextYellow = "\u001B[0m";
    
    private ContactsFromFile(){
        
    }
    public static ContactsFromFile getInstance() {
        if (instance == null) {
            instance = new ContactsFromFile();
        }
        return instance;
    }


    public void getContacts() {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(contactFile))) {
            String txt;
            while ((txt = reader.readLine()) != null) {
                builder.append(txt).append("\n");
            }
            System.out.println(setTextYellow + builder + turnOffTextYellow);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
