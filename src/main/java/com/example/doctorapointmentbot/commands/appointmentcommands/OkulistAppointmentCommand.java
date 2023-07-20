package com.example.doctorapointmentbot.commands.appointmentcommands;

import com.example.doctorapointmentbot.commands.ProcessCommand;
import com.example.doctorapointmentbot.helpers.AppointmentHelper;
import com.example.doctorapointmentbot.helpers.Doctors;
import com.example.doctorapointmentbot.helpers.UserHelper;
import com.example.doctorapointmentbot.models.AppointmentModel;
import com.example.doctorapointmentbot.models.UserModel;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import java.util.List;

@Component
public class OkulistAppointmentCommand extends DoctorsAppointmentCommand implements ProcessCommand {

    SendMessage message = new SendMessage();

    @Override
    public SendMessage start(Update update) {
        if (!update.getMessage().getText().equals("Окулист")) {
            return null;
        }

        UserModel user = UserHelper.findUser(update.getMessage().getChatId().toString());
        AppointmentModel appointment = new AppointmentModel();
        appointment.setTelegramID(user.getTelegramID());
        appointment.setDoctor(Doctors.OKULIST);
        AppointmentHelper.save(appointment);

        user.setAppointmentID(appointment.getId());
        UserHelper.saveUser(user);

        return sendDefaultMessage(update);
    }

    @Override
    public SendMessage sendDefaultMessage(Update update) {

        message.setChatId(update.getMessage().getChatId().toString());
        message.setText("Выберите подходящее время:");

        List<String> appointmentTime = AppointmentHelper.getFreeIntervals(Doctors.OKULIST);

        if (appointmentTime == null){
            message.setText("Свободных записей нет");
        } else {
            List<KeyboardRow> rowList = this.setTimeIntervals(appointmentTime);
            ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();
            markup.setKeyboard(rowList);
            message.setReplyMarkup(markup);
        }

        return message;
    }
}
