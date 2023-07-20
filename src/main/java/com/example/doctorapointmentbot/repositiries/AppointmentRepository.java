package com.example.doctorapointmentbot.repositiries;

import com.example.doctorapointmentbot.helpers.Doctors;
import com.example.doctorapointmentbot.models.AppointmentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<AppointmentModel, Long> {
    List<AppointmentModel> findAppointmentModelsByDoctor(Doctors doctor);

    AppointmentModel findAppointmentModelById(Long id);

    List<AppointmentModel> findAllByTelegramID(String tgID);

}

