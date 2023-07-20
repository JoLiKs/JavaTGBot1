package com.example.doctorapointmentbot.helpers;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class TimeIntervals {

    private List<String> timeIntervals = new ArrayList<>();

    public TimeIntervals() {
        for (int i = 0; i < 10; i++) {
            this.timeIntervals.add(10 + i + ".00");
        }
    }
}
