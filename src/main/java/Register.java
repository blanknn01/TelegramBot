import database.database;

public class Register {

    public void reg(String msg) {
        database data = new database();
        data.register(msg);
    }
}
