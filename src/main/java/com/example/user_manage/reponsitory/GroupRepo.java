package com.example.user_manage.reponsitory;

import com.example.user_manage.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepo extends JpaRepository<Group,Long> {
}
