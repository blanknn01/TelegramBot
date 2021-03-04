import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import java.sql.*;
class database extends Bot{
    public void sendMsg(Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);
        try {
            setButtons(sendMessage);
            sendMessage(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
    public  void GG(Message message, String text) {
        String connectionURL= "jdbc:postgresql://localhost:5432/simpledb";
        int a=39;
        char apo=(char) a;
        Connection con=null;
        ResultSet res= null;
        Statement stmt= null;
        try{
            Class.forName("org.postgresql.Driver");
            con= DriverManager.getConnection(connectionURL,"postgres","nur123assyL");
            stmt=con.createStatement();
            res=stmt.executeQuery("INSERT INTO public.users(id, name, surname) " +
                    "VALUES (5,'"
                    +text+"',"+"'"+text+"');");
       while(res.next())
               sendMsg(message, "done");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        finally{
            try{
                res.close();
                stmt.close();
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }

    }

}
