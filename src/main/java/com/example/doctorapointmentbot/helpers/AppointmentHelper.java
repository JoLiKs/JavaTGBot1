package com.example.doctorapointmentbot.helpers;

import com.example.doctorapointmentbot.models.AppointmentModel;
import com.example.doctorapointmentbot.repositiries.AppointmentRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AppointmentHelper {

    private AppointmentRepository repository;


    public AppointmentHelper(AppointmentRepository appointmentRepository) {
        this.repository = appointmentRepository;
        helper = this;
    }

    private static AppointmentHelper helper = null;

    public static void save(AppointmentModel appointment) {
        helper.repository.save(appointment);
    }

    public static AppointmentModel findAppointment(Long id) {
        return helper.repository.findAppointmentModelById(id);
    }

    public static List<AppointmentModel> findAllUserAppointments(String tgID) {
        return helper.repository.findAllByTelegramID(tgID);
    }

    public static void deleteAppointment(Long id) {
        helper.repository.deleteById(id);
    }

    public static List<String> getFreeIntervals(Doctors doctors) {

        TimeIntervals intervals = new TimeIntervals();
        List<AppointmentModel> appointmentsList = helper.repository.findAppointmentModelsByDoctor(doctors);

        List<String> freeIntervals;
        freeIntervals = intervals.getTimeIntervals();

        List<String> bookedIntervals = new ArrayList<>();
        for (AppointmentModel appointment : appointmentsList) {
            for (String interval : freeIntervals) {
                if (appointment.getTime() != null && appointment.getTime().equals(interval))
                    bookedIntervals.add(appointment.getTime());
            }
        }
        freeIntervals.removeAll(bookedIntervals);
        return freeIntervals;
    }

}
