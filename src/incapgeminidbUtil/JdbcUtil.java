package incapgeminidbUtil;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class JdbcUtil {
    public Connection getConnection(){
        Connection con=null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("Driver Loaded");

        try {
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/CUIMS", "sujoyray", "abcde");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Connection Established");
        
        return con;

    }
}
