import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;
import org.telegram.telegrambots.generics.LongPollingBot;

public class Bot extends TelegramLongPollingBot{
    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi= new TelegramBotsApi();
        try{
            telegramBotsApi.registerBot((LongPollingBot) new Bot());
        } catch(TelegramApiRequestException e)
        {
            e.printStackTrace();
        }
    }

    public void sendMsg(Message message,String text)
    {
        SendMessage sendMessage=new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);
        try{
            sendMessage(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void onUpdateReceived(Update update)
    {
        Message message=update.getMessage();
        if(message!=null && message.hasText())
        {
            switch (message.getText())
            {
                case"/help":
                    sendMsg(message,"How can I help you");
                            break;
                case "/settings":
                    sendMsg(message,"what we will do");
                            break;
            }
        }
    }
    public String getBotUsername(){
        return "Test";
    }
    public String hetToken(){
        return null;
    }

    @Override
    public String getBotToken() {
        return "1696659338:AAFmf_iy3jOEQ7MuRbdFPFTPvaQTp3LGCTs";
    }
}



