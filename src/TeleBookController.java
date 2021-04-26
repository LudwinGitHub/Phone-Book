import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class TeleBookController {
    private TeleBook teleBook = new TeleBook();
    private Scanner scanner = new Scanner(System.in);

    public void mainLoop(){
        TeleBookOptions teleBookOptions = null;
        do {
            showOptions();
        try {
            teleBookOptions = chooseOption();
            executeOption(teleBookOptions);
        } catch (NoSuchElementException e){
            System.out.println("Wrong option");
        }
    } while(teleBookOptions != TeleBookOptions.EXIT);
}
    private void showOptions(){
        for (TeleBookOptions value : TeleBookOptions.values()) {
            System.out.println(value);
        }
    }

    public TeleBookController(){
        teleBook = DataApp.read();
    }

    private TeleBookOptions chooseOption(){
        int option = scanner.nextInt();
        scanner.nextLine();
        return TeleBookOptions.numberToEnum(option);

    }
    private void executeOption(TeleBookOptions teleBookOptions){
        switch (teleBookOptions){
            case ADD:
                addContact();
                break;
            case DELETE:
                delete();
                break;
            case SEARCH_NAME:
                searchByName();
                break;
            case SEARCH_NUMBER:
                searchByNumber();
                break;
            case EXIT:
                close();
        }

    }

    private void addContact() {
        System.out.println("Name: ");
        String name = scanner.nextLine();
        System.out.println("Number: ");
        String number = scanner.nextLine();
        try {
            boolean add = teleBook.add(name, number);
            if (add){
                System.out.println("Contact added");
            } else {
                System.out.println("Contact already added");
            }
        } catch (IllegalArgumentException e){
            System.err.println("Name and number are empty, try again");
        }
    }

    private void delete(){
        System.out.println("Delete contact: ");
        String name = scanner.nextLine();
        boolean deleted = teleBook.remove(name);
        if (deleted){
            System.out.println("Contact deleted");
        } else {
            System.out.println("Contact not found");
        }
    }
    private void searchByNumber(){
        System.out.println("Search number: ");
        String telephone = scanner.nextLine();
        List<Contact> searchNumber = teleBook.findByNumber(telephone);
        if (searchNumber.isEmpty()) {
            System.out.println("No data :(");
        } else {
            searchNumber.forEach(System.out::println);
        }
    }
    private void searchByName(){
        System.out.println("Search name: ");
        String name = scanner.nextLine();
        List<Contact> searchName = teleBook.findByName(name);
        if (searchName.isEmpty()){
            System.out.println("No data");
        } else {
            searchName.forEach(System.out::println);
        }
    }
    private void close(){
        scanner.close();
        try {
            DataApp.saveFile(teleBook);
            System.out.println("Contacts saved");
        } catch (IOException e) {
            System.out.println("Save failure");
        }
        System.out.println("Bye bye");
    }

}
