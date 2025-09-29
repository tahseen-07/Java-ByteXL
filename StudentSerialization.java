import java.io.*;

public class StudentSerialization {

    // Inner Student class
    static class Student implements Serializable {
        private int studentID;
        private String name;
        private String grade;

        public Student(int studentID, String name, String grade) {
            this.studentID = studentID;
            this.name = name;
            this.grade = grade;
        }

        @Override
        public String toString() {
            return "ID: " + studentID + ", Name: " + name + ", Grade: " + grade;
        }
    }

    public static void main(String[] args) {
        Student s1 = new Student(101, "Alice", "A");

        // Serialization
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("student.ser"))) {
            oos.writeObject(s1);
            System.out.println("Student object serialized successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Deserialization
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("student.ser"))) {
            Student s2 = (Student) ois.readObject();
            System.out.println("Deserialized Student: " + s2);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
