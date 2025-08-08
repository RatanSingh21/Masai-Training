public class Methods {

    // For word count......
    private String words;

    // For duplicates
    private int duplicate;

    public String getWords() {
        return words;
    }

    public void setWords(String words) {
        this.words = words;
    }

    public Methods(String words) {
        this.words = words;
    }

    public int getDuplicate() {
        return duplicate;
    }

    public void setDuplicate(int duplicate) {
        this.duplicate = duplicate;
    }

    public Methods(int duplicate) {
        this.duplicate = duplicate;
    }
}
