package com.example.user_manage.reponsitory;

import com.example.user_manage.entity.UserGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserGroupRepo extends JpaRepository<UserGroup,Long> {



    @Query("select ug from UserGroup ug where ug.user.id = :userId and ug.group.id = :groupId")
    Optional<UserGroup> findByUserIdAndGroupId(Long userId,Long groupId);
}
