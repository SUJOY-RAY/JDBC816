package incapgeminiservicefactory;

import incapgeminiservice.IStudentService;
import incapgeminiservice.StudentServiceImpl;

public class StudentServiceFactory {
    private static IStudentService studentService = null;

    private StudentServiceFactory() {

    }

    public static IStudentService getStudentService() {
        if (studentService == null) {
            studentService = new StudentServiceImpl();

        }
        return studentService;
    }

}
