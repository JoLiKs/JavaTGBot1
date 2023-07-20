package com.example.doctorapointmentbot.models;


import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "telegram_user")
@Data
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String telegramUserName;
    private String name;
    private String telegramID;
    private Long appointmentID;

}
