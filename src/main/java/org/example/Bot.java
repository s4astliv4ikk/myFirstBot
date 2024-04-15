package org.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.ForwardMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

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
//        var msg = update.getMessage();
//        var user = msg.getChat();
//        var id = msg.getChatId();
//        var userName = user.getUserName();
//        var firstName = user.getFirstName();
//        var lastName = user.getLastName();



//        switch (msg.getText()){                              //получение команды
//            case "/start":
//                if (Objects.isNull(userName)){
//                    sendMessage(id, "Привет, " + user.getFirstName() + "!");
//                }else {
//                    sendMessage(id, "Привет, " + user.getUserName() + "!");
//                };
//                break;
//            case "/help": sendMessage(id, user.getFirstName() + ", это мой первый бот на котором я отрабатываю азы Java.");
//                break;
//            default: sendMessage(id, "Введена неверная команда, попробуй /start или /help");
//        }

        if (update.hasMessage() && update.getMessage().hasText()){
            var msg = update.getMessage();
            var user = msg.getChat();
            var id = msg.getChatId();
            forwardMessage(id,msg.getText(),msg.getMessageId()); //персылка сообщений в мой час с ботом
            System.out.println(update);                          //вывод данных в консоль
            switch (msg.getText()){
                case "/start":
                    try {
                        execute(Buttons.button(id));
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    };
                break;
                default: sendMessage(id, "Введена неверная команда, попробуйте /start");
            }
        }else if (update.hasCallbackQuery()){
            String callData = update.getCallbackQuery().getData();
            Long cId = update.getCallbackQuery().getMessage().getChatId();
            switch (callData){
                case "Hi": sendMessage(cId, "Hi, " + update.getCallbackQuery().getFrom().getFirstName());
                break;
                case "Help": sendMessage(cId, update.getCallbackQuery().getFrom().getFirstName() + ", это мой первый бот на котором я отрабатываю азы Java.");
            }
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
