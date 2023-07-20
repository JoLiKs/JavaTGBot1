package com.example.doctorapointmentbot.config;

import com.example.doctorapointmentbot.AppointmentBot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Component
public class Initializer {

    final AppointmentBot bot;

    public Initializer(AppointmentBot appointmentBot) {
        this.bot = appointmentBot;
    }

    @EventListener({ContextRefreshedEvent.class})
    public void init() {

        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(bot);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
