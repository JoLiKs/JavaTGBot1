package com.example.doctorapointmentbot.helpers;

import com.example.doctorapointmentbot.models.UserModel;
import com.example.doctorapointmentbot.repositiries.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserHelper {

    final UserRepository repository;
    private static UserHelper helper = null;

    public UserHelper(UserRepository userRepository) {
        this.repository = userRepository;
        helper = this;
    }

    public static void saveUser(UserModel user) {
        helper.repository.save(user);
    }

    public static UserModel findUser(String chatID) {
        return helper.repository.findUserModelByTelegramID(chatID);
    }

}
