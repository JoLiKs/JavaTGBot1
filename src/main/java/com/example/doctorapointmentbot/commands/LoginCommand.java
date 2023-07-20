package com.example.doctorapointmentbot.commands;

import com.example.doctorapointmentbot.helpers.UserHelper;
import com.example.doctorapointmentbot.models.UserModel;
import com.example.doctorapointmentbot.repositiries.UserRepository;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.Collections;

@Component
public class LoginCommand implements ProcessCommand {

    KeyboardRow row = new KeyboardRow();
    ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();

    @Override
    public SendMessage start(Update update) {

        if (!update.getMessage().getText().equals("Зарегистрироваться") &&
                !update.getMessage().getText().equals("Оставить свое имя") &&
                !update.getMessage().getText().equals("Продолжить без имени")) {
            return null;
        }

        SendMessage message = new SendMessage();
        row.add(new KeyboardButton("Оставить свое имя"));
        row.add(new KeyboardButton("Продолжить без имени"));
        markup.setKeyboard(Collections.singletonList(row));
        markup.setResizeKeyboard(true);
        message.setChatId(update.getMessage().getChatId().toString());
        message.setText("Выберите действие:");
        message.setReplyMarkup(markup);

        UserModel user = new UserModel();
        user.setTelegramUserName(update.getMessage().getFrom().getUserName());
        user.setTelegramID(update.getMessage().getFrom().getId().toString());
        if (update.getMessage().getText().equals("Продолжить без имени")) {
            user.setName("без имени");
            saveUser(message, user);
        }
        if (update.getMessage().getText().equals("Оставить свое имя")) {
            user.setName(update.getMessage().getFrom().getFirstName());
            saveUser(message, user);
        }

        return message;
    }

    private void saveUser(SendMessage message, UserModel user) {
        UserHelper.saveUser(user);
        message.setText("Пользователь сохранен");
        row.remove("Оставить свое имя");
        row.remove("Продолжить без имени");
        row.add(new KeyboardButton("Записаться к врачу"));
        markup.setKeyboard(Collections.singletonList(row));
        message.setReplyMarkup(markup);
    }


    @Override
    public SendMessage sendDefaultMessage(Update update) {
        return null;
    }
}
