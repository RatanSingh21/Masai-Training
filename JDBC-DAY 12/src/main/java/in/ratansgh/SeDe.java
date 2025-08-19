package in.ratansgh;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;


// we can declare class inside a class
public class SeDe {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        Student s1 = new Student(1, "Ratan");

        // Serialization
        FileOutputStream fos = new FileOutputStream("student.ser");
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        oos.writeObject(s1);
        System.out.println("Object Serialised.... with new file in directory generated...");

        // Deserialization
//        FileInputStream fis = new FileInputStream("student.ser");
//        ObjectInputStream ois  = new ObjectInputStream(fis);
//        Student s2 = (Student) ois.readObject();
//
//        System.out.println("Object deserialized....");
//        System.out.println(s2);
    }

    // Serialization example ->>  Marker Interface ( serializable annotation ) -->> not scalable hence database is used..
    // used in gaming industry we used it SESSION SORT OF MANAGEMENT
    static class Student implements Serializable {

        int id;
        String name ;

        public Student(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }

    }
}
