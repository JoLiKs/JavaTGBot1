package com.example.doctorapointmentbot.helpers;

public enum Doctors {

    TERAPEVT("Терапевт"),
    OKULIST("Окулист"),
    LOR("Отоларинголог"),
    GASTROENTEROLOG("Гастроэнтеролог"),
    HIRURG("Хирург"),
    GINEKOLOG("Гинеколог");

    private String title;

    Doctors(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
