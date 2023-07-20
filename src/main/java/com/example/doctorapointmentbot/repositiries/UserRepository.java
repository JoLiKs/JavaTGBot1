package com.example.doctorapointmentbot.repositiries;

import com.example.doctorapointmentbot.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {

    UserModel findUserModelByTelegramID(String id);
}
