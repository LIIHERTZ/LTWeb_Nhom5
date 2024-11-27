package FurSPS.configs;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {	
	
	private static final String serverName ="localhost";
	private static final String dbname = "FurSPS";
	private static final String portNumber ="1433";
	private static final String instance ="";
	private static final String userID ="sa";
	private static final String password = "123..231";

	public static Connection getConnection() throws SQLException{
        try {
            String url = "jdbc:sqlserver://"+serverName+":"+portNumber+"\\" + instance + ";databaseName="+dbname;
            
            if (instance == null || instance.trim().isEmpty())
            	url = "jdbc:sqlserver://"+serverName+":"+portNumber+ ";databaseName="+dbname;
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            return DriverManager.getConnection(url, userID , password);
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return null;
    }
	
	public static void main (String[] args){
		try {
			new DBConnection();
			System.out.println(DBConnection.getConnection());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}

