package com.example.doctorapointmentbot.commands;

import com.example.doctorapointmentbot.helpers.AppointmentHelper;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.Collections;

@Component
public class CancelAppointmentCommand implements ProcessCommand {

    @Override
    public SendMessage start(Update update) {


        if (!update.getMessage().getText().startsWith("Запись ")) {
            return null;
        }

        String appointmentRecord = update.getMessage().getText();
        String appointmentID = appointmentRecord.substring(7);
        Long id = Long.parseLong(appointmentID);

        AppointmentHelper.deleteAppointment(id);

        SendMessage message = new SendMessage();
        message.setChatId(update.getMessage().getChatId().toString());
        message.setText("Запись отменена");

        KeyboardRow row = new KeyboardRow();
        row.add(new KeyboardButton("Записаться к врачу"));
        row.add(new KeyboardButton("Мои записи"));
        ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();
        markup.setKeyboard(Collections.singletonList(row));
        markup.setResizeKeyboard(true);
        message.setReplyMarkup(markup);

        return message;
    }

    @Override
    public SendMessage sendDefaultMessage(Update update) {
        return null;
    }
}
