import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws Exception {

        // getting current time
        LocalDate Today = LocalDate.now();
//        System.out.println(Today);

        // Creating specific date
        LocalDate date = LocalDate.of(2025,12,2);
//        System.out.println(date);

        // Adding or substracting date
        LocalDate today = LocalDate.now();
        LocalDate newdate = today.plusDays(2);
//        System.out.println(newdate);

        // Formatting and Parsing date

        // Object created
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        String dateStr = LocalDate.now().format(dtf);
//        System.out.println(dateStr);

        LocalDate datey = LocalDate.parse("08/08/2025", dtf);
//        System.out.println(datey);

        // Working with timezone
        ZonedDateTime dateTime = ZonedDateTime.now(ZoneId.of("America/New_York"));
        System.out.println(dateTime);

        // Measuring Time Difference
        Instant start = Instant.now();

        Thread.sleep(2000);

        Instant end = Instant.now();

        Duration duration = Duration.between(start,end);
        System.out.println("Difference in seconds are: " +duration.getSeconds());


    }
}