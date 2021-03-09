import database.CheckData;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.User;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;
import org.telegram.telegrambots.generics.LongPollingBot;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Bot extends TelegramLongPollingBot {

    public static void main(String[] args) {


        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot((LongPollingBot) new Bot());
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }

    public void sendMsg(Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setText(text);
        try {
            setButtons(sendMessage);
            sendMessage(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }


    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        Model model = new Model();
        if (message != null && message.hasText()) {
            switch (message.getText()) {
                case "/help":
                    sendMsg(message, "Hello there I am a Weather bot and I can find information about weather in concreate location so first " +
                            "of all I recommend you register yourself in our bot" +
                            "in order to make me better" +
                            "I will take some information about(only your username, other information will not handled)");
                    sendMsg(message, "So to register please press /register button or just type /register");
                    break;
                case "/settings":
                    User user = message.getFrom();
                    String userName = user.getUserName();
                    CheckData checkData = new CheckData();

                    try {
                        if (userName.equals(checkData.Check(userName))) {
                            sendMsg(message, "what we will do");
                        } else {
                            sendMsg(message, "please first register");
                        }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    break;
                case "/register": {
                    CheckData ccheckData = new CheckData();

                    try {
                        user = message.getFrom();
                        userName = user.getUserName();
                        if (userName.equals(ccheckData.Check(userName))) {
                            sendMsg(message, "You have already registered");
                        } else {
                            Register reg = new Register();
                            reg.reg(userName);
                        }

                    } catch (Exception e) {
                        e.printStackTrace();

                    }



                }
                break;
                default:
                    try {
                    sendMsg(message, Weather.getWeather(message.getText(), model));
                } catch (IOException e) {
                    sendMsg(message, "City not found");
                }
            }
        }
    }


    public void setButtons(SendMessage sendmessage) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendmessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);

        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboardRowList = new ArrayList<>();
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        KeyboardRow keyboardSecondRow = new KeyboardRow();
        KeyboardRow keyboardThirdRow = new KeyboardRow();
        keyboardFirstRow.add(new KeyboardButton("/help"));
        keyboardSecondRow.add(new KeyboardButton("/settings"));
        keyboardThirdRow.add(new KeyboardButton("/register"));

        keyboardRowList.add(keyboardFirstRow);
        keyboardRowList.add(keyboardSecondRow);
        keyboardRowList.add(keyboardThirdRow);
        replyKeyboardMarkup.setKeyboard(keyboardRowList);
    }

    public String getBotUsername() {
        return "Test";
    }

    public String hetToken() {
        return null;
    }

    @Override
    public String getBotToken() {
        return "1696659338:AAFmf_iy3jOEQ7MuRbdFPFTPvaQTp3LGCTs";
    }
}



