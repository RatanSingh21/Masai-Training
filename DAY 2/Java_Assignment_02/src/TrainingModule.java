public class TrainingModule {
    private String title;
    private int durationInHrs;

    // Getter & Setter
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDurationInHrs() {
        return durationInHrs;
    }

    public void setDurationInHrs(int durationInHrs) {
        this.durationInHrs = durationInHrs;
    }

    // Constructor
    public TrainingModule(String title, int durationInHrs) {
        this.title = title;
        this.durationInHrs = durationInHrs;
    }

    //Displaying contents
    public void display(){
        System.out.println("The Training Modules Information are as follows: ");
        System.out.println("Name: " + title);
        System.out.println("Duration Hours: " + durationInHrs);
    }
}
