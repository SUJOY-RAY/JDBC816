package incapgeminiservice;

import incapgeminidaoFactory.StudentDaoFactory;
import incapgeminidto.Student;
import incapgeminipersistence.IStudentDAO;

public class StudentServiceImpl implements IStudentService{
    private IStudentDAO studentDAO;


    @Override
    public String addStudent(Integer StudentUID, String StudentName, String StudentCourse, Integer StudentBatch) {
        studentDAO=StudentDaoFactory.getStudentDAO();
        if (studentDAO!=null) {
            return studentDAO.addStudent(StudentUID, StudentName, StudentCourse, StudentBatch);
        }
        else
            return "failure";
    }

    @Override
    public Student selectStudent(Integer StudentUID) {
        studentDAO=StudentDaoFactory.getStudentDAO();
        return studentDAO.selectStudent(StudentUID);
    }

    @Override
    public String updateStudent(Student StudentUID) {
        studentDAO=StudentDaoFactory.getStudentDAO();
        return studentDAO.updateStudent(StudentUID);
    }

    @Override
    public String deleteStudent(Integer StudentUID) {
        studentDAO=StudentDaoFactory.getStudentDAO();
        return studentDAO.deleteStudent(StudentUID);
    }

}
