package com.example.doctorapointmentbot.models;

import com.example.doctorapointmentbot.helpers.Doctors;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class AppointmentModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String telegramID;
    private String time;

    @Enumerated (EnumType.STRING)
    private Doctors doctor;

}
