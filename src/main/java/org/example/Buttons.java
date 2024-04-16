package org.example;
//https://habr.com/ru/articles/746370/ татья откуда взят код

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import java.util.List;
import java.util.ArrayList;

public class Buttons {
    public static SendMessage button (Long cid){
        SendMessage sm = new SendMessage();         //отправка начального сообщения
        sm.setChatId(cid.toString());
        sm.setText("Выберите один из вариантов");

        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();            //массив строк
        List<InlineKeyboardButton> rowInline1 = new ArrayList<>();                  //массив одной строки
        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();    //первая кнопка
        inlineKeyboardButton1.setText("Send Hi");                                   //надпись на кнопке
        inlineKeyboardButton1.setCallbackData("Hi");                                //инфа для меня как обрабатывать нажатие

        InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton();    //2ая кнопка
        inlineKeyboardButton2.setText("Help");
        inlineKeyboardButton2.setCallbackData("Help");

        rowInline1.add(inlineKeyboardButton1);                                      //добавление кнопок в строку
        rowInline1.add(inlineKeyboardButton2);

        rowsInline.add(rowInline1);                                                 //добавление кнопок в массив строк

        markupInline.setKeyboard(rowsInline);
        sm.setReplyMarkup(markupInline);
        return sm;
    }
}
