package com.example.doctorapointmentbot.commands;

import com.example.doctorapointmentbot.helpers.AppointmentHelper;
import com.example.doctorapointmentbot.models.AppointmentModel;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.Collections;
import java.util.List;

@Component
public class ViewAppointmentsCommand implements ProcessCommand {

    @Override
    public SendMessage start(Update update) {

        if (!update.getMessage().getText().equals("Мои записи")) {
            return null;
        }

        SendMessage message = new SendMessage();
        message.setChatId(update.getMessage().getChatId().toString());

        List<AppointmentModel> appointmentsList = AppointmentHelper.findAllUserAppointments(update.getMessage().getFrom().getId().toString());

        if (!appointmentsList.isEmpty()) {
            StringBuilder listBuilder = new StringBuilder();
            for (AppointmentModel appointment : appointmentsList) {
                listBuilder.append("Запись №: ");
                listBuilder.append(appointment.getId());
                listBuilder.append("\n");
                listBuilder.append(appointment.getDoctor().getTitle());
                listBuilder.append("\n");
                listBuilder.append(appointment.getTime());
                listBuilder.append("\n");
                listBuilder.append("__________");
                listBuilder.append("\n");
            }
            message.setText(listBuilder.toString());
        } else {
            message.setText("у Вас нет записей");
        }

        KeyboardRow row = new KeyboardRow();
        row.add(new KeyboardButton("Записаться к врачу"));
        row.add(new KeyboardButton("Выбрать запись для отмены"));
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
