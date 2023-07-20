package com.example.doctorapointmentbot;

import com.example.doctorapointmentbot.commands.*;
import com.example.doctorapointmentbot.commands.appointmentcommands.*;
import com.example.doctorapointmentbot.config.BotConfig;
import com.example.doctorapointmentbot.repositiries.UserRepository;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class AppointmentBot extends TelegramLongPollingBot {
    final BotConfig config;

    public AppointmentBot(UserRepository userRepository, BotConfig botConfig) {
        this.config = botConfig;
    }

    @Override
    public String getBotUsername() {
        return config.getBotName();
    }

    @Override
    public String getBotToken() {
        return config.getToken();
    }

    @Override
    public void onUpdateReceived(Update update) {

        SendMessage message = new SendMessage();
        message.setChatId(update.getMessage().getChatId().toString());
        message.setText("Нажмите, чтобы начать");

        KeyboardRow row = new KeyboardRow();
        row.add(new KeyboardButton("Старт"));
        ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();
        markup.setResizeKeyboard(true);
        markup.setKeyboard(Collections.singletonList(row));
        message.setReplyMarkup(markup);

        List<ProcessCommand> commands = new ArrayList<>();
        commands.add(new WelcomeCommand());
        commands.add(new LoginCommand());
        commands.add(new AppointmentCommand());
        commands.add(new TerapevtAppointmentCommand());
        commands.add(new OkulistAppointmentCommand());
        commands.add(new LorAppointmentCommand());
        commands.add(new GastroAppointmentCommand());
        commands.add(new HirurgAppointmentCommand());
        commands.add(new GinekologAppointmentCommand());
        commands.add(new ChooseTimeCommand());
        commands.add(new ViewAppointmentsCommand());
        commands.add(new ChooseAppointmentCommand());
        commands.add(new CancelAppointmentCommand());

        for (ProcessCommand command : commands) {
            SendMessage commandMessage;
            commandMessage = command.start(update);
            if (commandMessage != null) {
                message = commandMessage;
                break;
            }
        }

        try {
            execute(message);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }

    }
}
