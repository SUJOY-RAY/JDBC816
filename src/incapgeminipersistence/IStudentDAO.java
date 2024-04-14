package incapgeminipersistence;

import incapgeminidto.Student;

public interface IStudentDAO {
    
    public String addStudent(Integer StudentUID, String StudentName, String StudentCourse, Integer StudentBatch);

    public Student selectStudent(Integer StudentUID);
    
    public String updateStudent(Student StudentUID);
    
    public String deleteStudent(Integer StudentUID);
    
}