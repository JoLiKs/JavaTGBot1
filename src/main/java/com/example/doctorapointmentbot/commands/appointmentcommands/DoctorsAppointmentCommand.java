package com.example.doctorapointmentbot.commands.appointmentcommands;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public abstract class DoctorsAppointmentCommand {

    public List<KeyboardRow> setTimeIntervals(List<String> timeList) {

        List<KeyboardRow> rowList = new ArrayList<>();
        int rowCounter = 0;

        while (rowCounter < timeList.size()) {
            KeyboardRow row = new KeyboardRow();
            for (int i = rowCounter; (i < rowCounter + 5) && i < timeList.size(); i++) {
                row.add(new KeyboardButton(timeList.get(i)));
            }
            rowList.add(row);
            rowCounter += 5;
        }
        return rowList;
    }
}
