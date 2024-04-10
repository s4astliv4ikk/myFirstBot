package org.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.ForwardMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Objects;
import java.util.logging.Level;

public class Bot extends TelegramLongPollingBot {

    final private String BOT_TOKEN = "6710916054:AAGphNqeNuzdMiF0LlCH657FdddiuloGfJ4";
    final private String BOT_NAME = "MyFirstBot";

    @Override
    public String getBotUsername() {
        return BOT_NAME;
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }

    @Override
    public void onUpdateReceived(Update update) {
        var msg = update.getMessage();
        var user = msg.getChat();
        var id = user.getId();
        var userName = user.getUserName();
        var firstName = user.getFirstName();
        var lastName = user.getLastName();


        forwardMessage(id,msg.getText(),msg.getMessageId());
        System.out.println(update);
        switch (msg.getText()){
            case "/start":
                if (Objects.isNull(userName)){
                    sendMessage(id, "Привет, " + user.getFirstName() + "!");
                }else {
                    sendMessage(id, "Привет, " + user.getUserName() + "!");
                };
            break;
            case "/help": sendMessage(id, user.getFirstName() + ", это мой первый бот на котором я отрабатываю азы Java.");
            break;
            default: sendMessage(id, "Введена не верная команда, попробуй /start или /help");
        }
    }

    public void sendMessage (Long who, String msg){
        SendMessage sm = new SendMessage();
        sm.setChatId(who.toString());
        sm.setText(msg);
        try{
            execute(sm);
        }catch (TelegramApiException e) {
        }
    }

    public void forwardMessage (Long who, String msg, Integer mId){
        ForwardMessage fm = new ForwardMessage();
        fm.setChatId("937081213");      //мой чат с ботом
        fm.setFromChatId(who.toString());              //
        fm.setMessageId(mId);              //
        try{
            execute(fm);
        }catch (TelegramApiException e) {
        }
    }
}
