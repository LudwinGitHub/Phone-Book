import java.io.*;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DataApp {

    private static final String FILE_NAME = "Contacts.csv";

    public static void saveFile(TeleBook teleBook) throws IOException {
        var bufferedWriter = new BufferedWriter(new FileWriter(FILE_NAME));
        for (Contact contact : teleBook) {
            bufferedWriter.write(contact.toCSV());
            bufferedWriter.newLine();
        }
        bufferedWriter.close();
    }

    public static TeleBook read() {
        TeleBook book = null;
        try {
            var buffReader = new BufferedReader(new FileReader(FILE_NAME));
            Map<String, Contact> contacts = buffReader.lines()
                    .map(line -> line.split(";"))
                    .map(split -> new Contact(split[0], split[1]))
                    .collect(Collectors.toMap(Contact::getName, Function.identity()));
            book = new TeleBook(new TreeMap<>(contacts));
        } catch (FileNotFoundException e) {
            System.err.println("File not found");
        }
        return book != null ? book : new TeleBook();
    }
}