package incapgeminipersistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import incapgeminidbUtil.JdbcUtil;
import incapgeminidto.Student;

public class StudentDaoImpl implements IStudentDAO{
    Connection connection =null;
    PreparedStatement psmt=null;
    JdbcUtil connectionObject=new JdbcUtil();
    ResultSet resultSet=null;
    Student student=new Student();
    int rowAffected=0;
 
 
    //insert   
    @Override
    public String addStudent(Integer StudentUID, String StudentName, String StudentCourse, Integer StudentBatch) {
        String sqlInsertQuery="insert into studentdb values(?,?,?,?)";
        connection=connectionObject.getConnection();
        
        if (connection!=null) {
            try {
                psmt=connection.prepareStatement(sqlInsertQuery);
            } catch (SQLException e) {
                
                e.printStackTrace();
            }
            if (psmt!=null) {
                try {
                    psmt.setInt(1, StudentUID);
                } catch (SQLException e) {
                    
                    e.printStackTrace();
                }
                    try {
                        psmt.setString(2, StudentName);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    try {
                        psmt.setString(3, StudentCourse);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    try {
                        psmt.setInt(4, StudentBatch);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    try {
                        rowAffected=psmt.executeUpdate();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    if (rowAffected>0){
                        return "success";
                    }
                    else return "failure";
            }
        }
        return sqlInsertQuery;  
    }



///select    
    @Override
    public Student selectStudent(Integer StudentUID) {
        String sqlSearchQuery="select* from studentdb where StudentUID=?";
        connection=connectionObject.getConnection();
        if (connectionObject!=null) {
            try {
                psmt=connection.prepareStatement(sqlSearchQuery);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (psmt!=null) {
                try {
                    psmt.setInt(1, StudentUID);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                try {
                    resultSet=psmt.executeQuery();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (resultSet!=null) {
                try {
                    if (resultSet.next()) {
                        student=new Student();
                        student.setStudentUID(resultSet.getInt(1));
                        student.setStudentName(resultSet.getString(2));
                        student.setStudentCourse(resultSet.getString(3));
                        student.setStudentBatch(resultSet.getInt(4));
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return student;
    }

    @Override
    public String updateStudent(Student StudentUID) {

        String sqlUpdateQuery="update studentdb set StudentUID=?, StudentName=?,StudentCourse=?, StudentBatch=?";
        connection=connectionObject.getConnection();
        if (connection!=null) {
            try {
                psmt=connection.prepareStatement(sqlUpdateQuery);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }        
        if (psmt!=null) {
            try {
                psmt.setInt(1, student.getStudentUID());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                psmt.setString(2, student.getStudentName());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                psmt.setString(3, student.getStudentCourse());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                psmt.setInt(4, student.getStudentUID());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                rowAffected=psmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if(rowAffected==1){
                return "success";
            }
            return "failure";
        }


        return null;
    }

    @Override
    public String deleteStudent(Integer StudentUID) {
        String sqlDeleteQuery="delete from studentdb where studentUID=?";
        connection=connectionObject.getConnection();
        if (connection!=null) {
        try {
            psmt=connection.prepareStatement(sqlDeleteQuery);
            psmt.setInt(1, StudentUID);
            rowAffected=psmt.executeUpdate();
            if (rowAffected>0) {
                return "success";
            }
            else{
                return "delete failure";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        
        }
        return "failure";
        
    }

}
