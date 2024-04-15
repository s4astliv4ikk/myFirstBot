package org.example;
//https://habr.com/ru/articles/746370/ татья откуда взят код

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import java.util.List;
import java.util.ArrayList;

public class Buttons {
    public static SendMessage button (Long cid){
        SendMessage sm = new SendMessage();
        sm.setChatId(cid.toString());
        sm.setText("Выберите один из вариантов");

        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInline1 = new ArrayList<>();
        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Send Hi");
        inlineKeyboardButton1.setCallbackData("Hi");

        InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton();
        inlineKeyboardButton2.setText("Help");
        inlineKeyboardButton2.setCallbackData("Help");

        rowInline1.add(inlineKeyboardButton1);
        rowInline1.add(inlineKeyboardButton2);

        rowsInline.add(rowInline1);

        markupInline.setKeyboard(rowsInline);
        sm.setReplyMarkup(markupInline);
        return sm;
    }
}
