package com.example.doctorapointmentbot.commands;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface ProcessCommand {

    SendMessage start (Update update);

    SendMessage sendDefaultMessage (Update update);
}
