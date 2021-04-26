import java.util.NoSuchElementException;

public enum TeleBookOptions {
    ADD (0, "Add new contact"),
    SEARCH_NAME (1, "Search contact by name"),
    SEARCH_NUMBER (2, "Search contact by number"),
    DELETE (3, "Delete contact"),
    EXIT (4, "EXIT");

    private int optionNumber;
    private String description;

    TeleBookOptions(int optionNumber, String description) {
        this.optionNumber = optionNumber;
        this.description = description;
    }

    public int getOptionNumber() {
        return optionNumber;
    }

    static TeleBookOptions numberToEnum(int option){
        if (option >= values().length || option < 0)
            throw new NoSuchElementException("Wrong option");
        return values()[option];
    }
    @Override
    public String toString() {
        return optionNumber + ": " + description;
    }
}
