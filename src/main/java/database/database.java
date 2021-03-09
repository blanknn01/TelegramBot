package database;

import java.sql.*;

public class  database {

    public void register(String text) {
        String connectionURL = "jdbc:postgresql://localhost:5432/simpledb";
        Connection con = null;
        Statement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection(connectionURL, "postgres", "nur123assyL");
            stmt = con.createStatement();
            stmt.execute("INSERT INTO public.users( users) " +
                    "VALUES ('"
                    + text + "');");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                stmt.close();
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }

    }

}
