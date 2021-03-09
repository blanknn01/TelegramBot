package database;
import java.sql.*;
public class CheckData {
    public String userName;

    public String Check(String text) throws SQLException {
        String connectionURL = "jdbc:postgresql://localhost:5432/simpledb";
        Connection con = null;
        ResultSet res = null;
        Statement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection(connectionURL, "postgres", "nur123assyL");
            stmt = con.createStatement();
            res = stmt.executeQuery("SELECT * FROM public.users where users='" + text + "';");
            while(res.next())
            {
                userName= res.getString("users");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                res.close();
                stmt.close();
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
        return userName;
    }

}




