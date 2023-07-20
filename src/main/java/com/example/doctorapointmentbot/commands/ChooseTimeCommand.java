package com.example.doctorapointmentbot.commands;

import com.example.doctorapointmentbot.helpers.AppointmentHelper;
import com.example.doctorapointmentbot.helpers.TimeIntervals;
import com.example.doctorapointmentbot.helpers.UserHelper;
import com.example.doctorapointmentbot.models.AppointmentModel;
import com.example.doctorapointmentbot.models.UserModel;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.Collections;
import java.util.List;

@Component
public class ChooseTimeCommand implements ProcessCommand {
    @Override
    public SendMessage start(Update update) {

        TimeIntervals intervals = new TimeIntervals();
        List<String> timeIntervals = intervals.getTimeIntervals();
        boolean isTimeCommand = false;

        for (String interval : timeIntervals) {
            if (update.getMessage().getText().equals(interval)) {
                isTimeCommand = true;
            }
        }
        if (!isTimeCommand) {
            return null;
        }
        UserModel user = UserHelper.findUser(update.getMessage().getFrom().getId().toString());
        AppointmentModel appointment = AppointmentHelper.findAppointment(user.getAppointmentID());

        appointment.setTime(update.getMessage().getText());
        AppointmentHelper.save(appointment);

        SendMessage message = new SendMessage();
        message.setChatId(update.getMessage().getChatId().toString());
        message.setText("Вы успешно записаны");

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
