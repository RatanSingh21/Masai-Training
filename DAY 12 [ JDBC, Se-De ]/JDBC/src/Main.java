import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException {


        Class.forName("com.mysql.cj.jdbc.Driver");

        String url = "jdbc:mysql://localhost:[from MYSQL]/[DATABASE_NAME]";


        // user buffer data // class data for same -->> more safe and faster than scanner class
        Scanner sc = new Scanner(System.in);

//
//        // create this database -->> variable name should be same -->> REQUIRED FOR INSERTING DATA
//        System.out.println("Insert Course ID: " );
//        int course_ID = Integer.parseInt(sc.nextLine()); // type conversion needed to block uncertainity
//
//        System.out.println("Enter the course name: ");
//        String course_name = sc.nextLine().trim();


        // this pushes the code in Mysql
        try(Connection connection = DriverManager.getConnection(url, "username", "password");){

            if (connection != null){


                // DML
                // FOR INSERTING DATA
//                PreparedStatement pst =  connection.prepareStatement("Insert into course value (? ,? )");
//                pst.setInt(1, course_ID);
//                pst.setString(2, course_name);

                // FOR UPDATING DATA
//                PreparedStatement pst =  connection.prepareStatement("update course  set course_name = ? where course_ID = (from Database)" );
//                pst.setString(2, course_name);

                // FOR DELETING DATA
                PreparedStatement pst =  connection.prepareStatement("DELETE FROM COURSE WHERE id = ?" );


                // DQL
                // can't use preparedStatement so we use resultset
                ResultSet rs = pst.executeQuery();
                if(rs.next()){
                    int id = rs.getInt("id");
                    String course_name = rs.getString("course_name");
                    System.out.println("the course name is :" + course_name + " for this id " + id);
                }else{
                    System.out.println("no course with this id ");
                }



                int i = pst.executeUpdate();

                if (i > 0 ){
                    System.out.println("record inserted");
                }
            }

        } catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }
}