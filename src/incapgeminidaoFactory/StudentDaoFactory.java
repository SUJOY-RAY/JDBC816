package incapgeminidaoFactory;

import incapgeminipersistence.IStudentDAO;
import incapgeminipersistence.StudentDaoImpl;

public class StudentDaoFactory {
    public static IStudentDAO studentDAO=null;
    private StudentDaoFactory(){

    }

    public static IStudentDAO getStudentDAO(){
        if (studentDAO==null) {
            studentDAO=new StudentDaoImpl();
        }
        return studentDAO;
    }
}
