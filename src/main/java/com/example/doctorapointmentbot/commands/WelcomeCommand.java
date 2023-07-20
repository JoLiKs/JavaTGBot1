package com.example.doctorapointmentbot.commands;

import com.example.doctorapointmentbot.helpers.UserHelper;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.Collections;

@Component
public class WelcomeCommand implements ProcessCommand {

    @Override
    public SendMessage start(Update update) {

        if (!update.getMessage().getText().equals("Старт")) {
            return null;
        }

        SendMessage message = new SendMessage();
        message.setChatId(update.getMessage().getChatId().toString());

        if (update.getMessage().getText().equals("Старт")) {
            KeyboardRow keyboard = new KeyboardRow();
            if (UserHelper.findUser(update.getMessage().getFrom().getId().toString()) == null) {
                keyboard.add(new KeyboardButton("Зарегистрироваться"));
            } else {
                keyboard.add(new KeyboardButton("Записаться к врачу"));
                keyboard.add(new KeyboardButton("Мои записи"));
            }

            ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();
            markup.setKeyboard(Collections.singletonList(keyboard));
            markup.setResizeKeyboard(true);
            message.setReplyMarkup(markup);
            message.setText("Нажмите, чтобы продолжить");
        }
        return message;
    }

    @Override
    public SendMessage sendDefaultMessage(Update update) {
        return null;
    }
}
