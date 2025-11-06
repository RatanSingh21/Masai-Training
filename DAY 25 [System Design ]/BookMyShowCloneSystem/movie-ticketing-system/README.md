
# Movie Ticketing System (BookMyShow Clone)
Simplified Spring Boot + JPA project skeleton showing core booking flow with concurrency handling.

## How to run
1. Ensure Java 17+ and Gradle installed.
2. Configure PostgreSQL settings in `src/main/resources/application.yml`.
3. Run:
   ```
   ./gradlew bootRun
   ```
   or build with:
   ```
   ./gradlew build
   java -jar build/libs/movie-ticketing-system-0.0.1-SNAPSHOT.jar
   ```

Import SQL sample data is in `src/main/resources/import.sql`.

