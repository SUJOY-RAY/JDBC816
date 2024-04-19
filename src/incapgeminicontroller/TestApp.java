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
                    scanner.close(); 
                    System.exit(0); 
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
            }
        }
    }

    
    private static void updateStudent() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Student UID to be updated:");
        int studentUID = scanner.nextInt();
        scanner.nextLine();
        
        IStudentService studentService = StudentServiceFactory.getStudentService();
        Student student = studentService.selectStudent(studentUID);
        
        if (student.getStudentUID() != null) {
            Student newStudent = new Student();
            newStudent.setStudentUID(student.getStudentUID());
            
            System.out.println("Student UID is: " + student.getStudentUID());
            
            System.out.println("Student old name is: " + student.getStudentName() + " Enter new name:");
            String newName = scanner.nextLine();
            if (!newName.isEmpty()) {
                newStudent.setStudentName(newName);
            } else {
                newStudent.setStudentName(student.getStudentName());
            }
            
            System.out.println("Student old course is: " + student.getStudentCourse() + " Enter new course:");
            String newCourse = scanner.nextLine();
            if (!newCourse.isEmpty()) {
                newStudent.setStudentCourse(newCourse);
            } else {
                newStudent.setStudentCourse(student.getStudentCourse());
            }
    
            System.out.println("Student old batch is: " + student.getStudentBatch() + " Enter new batch:");
            int newBatch = scanner.nextInt();
            if (newBatch != 0) {
                newStudent.setStudentBatch(newBatch);
            } else {
                newStudent.setStudentBatch(student.getStudentBatch());
            }
            
            String status = studentService.updateStudent(newStudent);
            if (status.equalsIgnoreCase("success")) {
                System.out.println("Record updated successfully");
            } else {
                System.out.println("Record updation failed");
            }
        } else {
            System.out.println("Record not found for the given Student UID: " + studentUID);
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
