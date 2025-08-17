package in.ratansgh.enums;

public enum AccountType {

    SAVINGS("Savings Accounts"),
    CURRENT("Current Accounts");

    private final String displayName;

    AccountType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return "AccountType{" +
                "displayName='" + displayName + '\'' +
                '}';
    }
}
