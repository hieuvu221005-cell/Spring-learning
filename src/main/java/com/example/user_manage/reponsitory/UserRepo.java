package com.example.user_manage.reponsitory;

import com.example.user_manage.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface UserRepo extends JpaRepository<User,Long> {

    List<User> findAllByAgeLessThan(int age);
    //SELECT * FROM user WHERE age < ?
    List<User> findAllByUserNameLike(String firstName);
    //SELECT * FROM user WHERE user_name LIKE ?
    List<User> findByCreateDateBefore(LocalDate createDate);
    //SELECT * FROM user WHERE create_date < ?
    List<User> findByFirst_nameIgnoreCase(String firstName);
    //SELECT * FROM user WHERE LOWER(first_name) = LOWER(?)
    List<User> findByAgeIn(List<Integer> age);
    //SELECT * FROM user WHERE age IN (2, 3, 4, 5)
    List<User> findByCreateDateAfter(LocalDate createDate);
    //SELECT * FROM user WHERE create_date > ?

    @Modifying
    @Query("update User u set u.firstName = 'Nguyen' where u.firstName like '%Vu%'")
    void updateUserByFirstName();

    @Modifying
    @Query("delete from User u where u.firstName like '%Nguyen%'")
    void deleteUserByFirstName();
}
