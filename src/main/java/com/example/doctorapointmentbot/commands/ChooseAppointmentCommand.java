package com.example.doctorapointmentbot.commands;

import com.example.doctorapointmentbot.helpers.AppointmentHelper;
import com.example.doctorapointmentbot.models.AppointmentModel;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

@Component
public class ChooseAppointmentCommand implements ProcessCommand {

    SendMessage message = new SendMessage();
    ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();

    @Override
    public SendMessage start(Update update) {

        if (!update.getMessage().getText().equals("Выбрать запись для отмены")) {
            return null;
        }

        message.setChatId(update.getMessage().getChatId().toString());
        message.setText("Введите номер записи для отмены:");

        List<AppointmentModel> appointmentList = AppointmentHelper.findAllUserAppointments(update.getMessage().getFrom().getId().toString());
        List<KeyboardRow> rowList = new ArrayList<>();

        if (!appointmentList.isEmpty()) {
            KeyboardRow row1 = new KeyboardRow();
            row1.add(new KeyboardButton("Запись " + appointmentList.get(0).getId()));
            rowList.add(row1);
            if (appointmentList.size() > 1) {
                KeyboardRow row2 = new KeyboardRow();
                for (int i = 1; i < appointmentList.size(); i++) {
                    row2.add(new KeyboardButton("Запись " + appointmentList.get(i).getId()));
                }
                rowList.add(row2);
            }
        }
        markup.setKeyboard(rowList);
        markup.setResizeKeyboard(true);
        message.setReplyMarkup(markup);

        return message;
    }

    @Override
    public SendMessage sendDefaultMessage(Update update) {
        return null;
    }
}
