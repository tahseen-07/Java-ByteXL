import java.io.*;
import java.util.*;

class Employee implements Serializable {
    String name;
    int id;
    String designation;
    double salary;

    Employee(String name, int id, String designation, double salary) {
        this.name = name;
        this.id = id;
        this.designation = designation;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Designation: " + designation + ", Salary: " + salary;
    }
}

// Helper class to append objects to existing file
class AppendableObjectOutputStream extends ObjectOutputStream {
    AppendableObjectOutputStream(OutputStream out) throws IOException {
        super(out);
    }

    protected void writeStreamHeader() throws IOException {}
}

public class EmployeeManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String fileName = "employees.ser";

        while (true) {
            System.out.println("\n--- Employee Management System ---");
            System.out.println("1. Add Employee");
            System.out.println("2. Display All Employees");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter Employee ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Designation: ");
                    String designation = scanner.nextLine();
                    System.out.print("Enter Salary: ");
                    double salary = scanner.nextDouble();
                    scanner.nextLine();

                    Employee emp = new Employee(name, id, designation, salary);
                    try {
                        FileOutputStream fos = new FileOutputStream(fileName, true);
                        ObjectOutputStream oos;
                        File file = new File(fileName);
                        if (file.length() == 0) {
                            oos = new ObjectOutputStream(fos);
                        } else {
                            oos = new AppendableObjectOutputStream(fos);
                        }
                        oos.writeObject(emp);
                        oos.close();
                        fos.close();
                        System.out.println("Employee added successfully!");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;

                case 2:
                    try {
                        FileInputStream fis = new FileInputStream(fileName);
                        ObjectInputStream ois = new ObjectInputStream(fis);
                        System.out.println("\n--- Employee Records ---");
                        while (true) {
                            try {
                                Employee e = (Employee) ois.readObject();
                                System.out.println(e);
                            } catch (EOFException eof) {
                                break;
                            }
                        }
                        ois.close();
                        fis.close();
                    } catch (IOException | ClassNotFoundException e) {
                        System.out.println("No employee records found.");
                    }
                    break;

                case 3:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
