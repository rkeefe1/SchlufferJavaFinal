package com.example.demo.messages;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.example.demo.users.UserModel;
import com.example.demo.messages.Message;


@Repository
@CrossOrigin(origins="http://localhost:4200")
public interface MessageRepository extends JpaRepository<Message, Long>, MessageRepositoryCustom {

    List<Message> findByIdAndToUser(Integer id, UserModel user);

    @Query("SELECT m FROM Message m JOIN FETCH m.author JOIN FETCH m.toUser WHERE m.id = :id AND m.toUser = :user")
    List<Message> findMessageByIdAndUser(@Param("id") Long id);
}
