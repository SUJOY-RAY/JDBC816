package incapgeminicontroller;

import java.util.Scanner;
import incapgeminidto.Student;
import incapgeminiservice.IStudentService;
import incapgeminiservicefactory.StudentServiceFactory;

public class TestApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            // Display menu options
            System.out.println("Choose an option:");
            System.out.println("1. Insert Student");
            System.out.println("2. Update Student");
            System.out.println("3. Delete Student");
            System.out.println("4. Get Student");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    insertStudent();
                    break;
                case 2:
                    updateStudent();
                    break;
                case 3:
                    deleteStudent();
                    break;
                case 4:
                    getStudent();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    scanner.close(); // Close scanner before exiting
                    System.exit(0); // Exit the program
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
            }
        }
    }

    private static void updateStudent() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the UID to be updated");
        int studentUID = scanner.nextInt();
        scanner.nextLine();
        IStudentService studentService = StudentServiceFactory.getStudentService();
        Student student = studentService.selectStudent(studentUID);
        if (student.getStudentUID() != null) {
            Student newStudent = new Student();

            System.out.println("Student UID is: " + student.getStudentUID());

            System.out.println("Student old Name: " + student.getStudentName() + "\n" + "Enter new Name: ");
            String newName = scanner.nextLine();
            if (newName.trim().isEmpty()) {
                newStudent.setStudentName(student.getStudentName());
            } else {
                newStudent.setStudentName(newName);
            }

            System.out.println("Student old Course: " + student.getStudentCourse() + "\n" + "Enter new Course: ");
            String newCourse = scanner.nextLine();
            if (newCourse.trim().isEmpty()) {
                newStudent.setStudentCourse(student.getStudentCourse());
            } else {
                newStudent.setStudentCourse(newCourse);
            }

            System.out.println("Student old Batch: " + student.getStudentBatch() + "\n" + "Enter new Batch: ");
            int newBatch = scanner.nextInt();
            scanner.nextLine();
            if (newBatch == 0) {
                newStudent.setStudentBatch(student.getStudentBatch());
            } else {
                newStudent.setStudentBatch(newBatch);
            }

            String status = studentService.updateStudent(newStudent);
            if (status.equalsIgnoreCase("success")) {
                System.out.println("Record updated successfully");
                System.out.println("StudentUID \tStudentName\t StudentCourse\t StudentBatch");
                System.out.println(newStudent.getStudentUID() + "\t\t" + newStudent.getStudentName() + "\t\t"
                        + newStudent.getStudentCourse() + "\t\t" + newStudent.getStudentBatch());
            } else {
                System.out.println("Record updation failed ");
            }
        }
    }

    private static void deleteStudent() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Student UID");
        int studentUID = scanner.nextInt();
        IStudentService studentService = StudentServiceFactory.getStudentService();
        String message = studentService.deleteStudent(studentUID);

        switch (message.toLowerCase()) {
            case "success":
                System.out.println("Record for " + studentUID + " deleted successfully");
                break;
            case "delete failure":
                System.out.println("Record not found for the given student UID: " + studentUID);
                break;
            default:
                System.out.println("Record deletion failed");
                break;
        }
    }

    private static void getStudent() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Student UID");
        int sid = scanner.nextInt();
        IStudentService studentService = StudentServiceFactory.getStudentService();
        Student studentObject = studentService.selectStudent(sid);
        if (studentObject.getStudentUID() == null) {
            System.out.println("Record not found for given student UID: " + sid);
        } else {
            System.out.println("StudentUID \tStudentName\t StudentCourse\t StudentBatch");
            System.out.println(studentObject.getStudentUID() + "\t\t" + studentObject.getStudentName() + "\t\t"
                    + studentObject.getStudentCourse() + "\t\t" + studentObject.getStudentBatch());
        }
    }

    private static void insertStudent() {
        IStudentService studentService = StudentServiceFactory.getStudentService();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Student UID");
        int sUID = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter Student Name");
        String sName = scanner.nextLine();
        System.out.println("Enter Student Course");
        String sCourse = scanner.nextLine();
        System.out.println("Enter Student Batch");
        int sBatch = scanner.nextInt();
        scanner.nextLine();

        String message = studentService.addStudent(sUID, sName, sCourse, sBatch);

        if (message.equalsIgnoreCase("success")) {
            System.out.println("Record inserted successfully.");
        } else {
            System.out.println("Record insertion failed");
        }
    }
}
