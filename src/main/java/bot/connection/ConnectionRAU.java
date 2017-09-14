package bot.connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionRAU {

    private static Connection conn;
    public static Connection getConnection() {
        if (conn == null) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/raubot", "root", "1234");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return conn;
        } else {
            return conn;
        }
    }
}
