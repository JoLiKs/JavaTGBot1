package com.example.doctorapointmentbot.commands;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class AppointmentCommand implements ProcessCommand {

    @Override
    public SendMessage start(Update update) {

        if (!update.getMessage().getText().equals("Записаться к врачу")) {
            return null;
        }

        SendMessage message = new SendMessage();
        message.setChatId(update.getMessage().getChatId().toString());

        KeyboardRow row1 = new KeyboardRow();
        row1.add(new KeyboardButton("Терапевт"));
        row1.add(new KeyboardButton("Хирург"));

        KeyboardRow row2 = new KeyboardRow();
        row2.add(new KeyboardButton("Отоларинголог"));
        row2.add(new KeyboardButton("Окулист"));

        KeyboardRow row3 = new KeyboardRow();
        row3.add(new KeyboardButton("Гастроэнтеролог"));
        row3.add(new KeyboardButton("Гинеколог"));

        List<KeyboardRow> rowsList = new ArrayList<>();
        rowsList.add(row1);
        rowsList.add(row2);
        rowsList.add(row3);

        ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();
        markup.setKeyboard(rowsList);

        message.setText("Выберите врача:");
        message.setReplyMarkup(markup);

        return message;
    }

    @Override
    public SendMessage sendDefaultMessage(Update update) {
        return null;
    }
}
