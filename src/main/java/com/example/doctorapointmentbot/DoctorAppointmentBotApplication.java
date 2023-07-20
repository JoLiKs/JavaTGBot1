package com.example.doctorapointmentbot;

import com.example.doctorapointmentbot.repositiries.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@SpringBootApplication
public class DoctorAppointmentBotApplication {

    public static void main(String[] args) {
        SpringApplication.run(DoctorAppointmentBotApplication.class, args);

    }

}
