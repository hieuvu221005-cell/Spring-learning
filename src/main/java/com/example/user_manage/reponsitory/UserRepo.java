package com.example.user_manage.reponsitory;

import com.example.user_manage.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface UserRepo extends JpaRepository<User,Long> {

    @Modifying
    @Query("update User u set u.firstName = 'Nguyen' where u.firstName like '%Vu%'")
    void updateUserByFirstName();

    @Modifying
    @Query("delete from User u where u.firstName like '%Nguyen%'")
    void deleteUserByFirstName();
}
