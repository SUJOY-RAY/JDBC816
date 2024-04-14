package incapgeminidto;

import java.io.Serializable;

public class Student implements Serializable {
    private static final long serialVersionUID=1L;
    private Integer StudentUID;
    private String StudentName;
    private String StudentCourse;
    private Integer StudentBatch;

    public Integer getStudentUID() {
        return this.StudentUID;
    }

    public void setStudentUID(Integer StudentUID) {
        this.StudentUID = StudentUID;
    }

    public String getStudentName() {
        return this.StudentName;
    }

    public void setStudentName(String StudentName) {
        this.StudentName = StudentName;
    }

    public String getStudentCourse() {
        return this.StudentCourse;
    }

    public void setStudentCourse(String StudentCourse) {
        this.StudentCourse = StudentCourse;
    }

    public Integer getStudentBatch() {
        return this.StudentBatch;
    }

    public void setStudentBatch(Integer StudentBatch) {
        this.StudentBatch = StudentBatch;
    }
    
    public String toString(){
        return "Student [studentUID="+StudentUID+",studentName="+StudentName+",studentCourse="+StudentCourse+", StudentBatch="+StudentBatch+"]";
    }
}
