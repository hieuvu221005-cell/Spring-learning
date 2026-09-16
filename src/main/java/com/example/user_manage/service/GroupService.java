package com.example.user_manage.service;
import com.example.user_manage.dto.request.GroupReq;
import com.example.user_manage.entity.Group;

import java.util.List;

public interface GroupService {
    Group getById(Long id);
    List<Group> getAll();
    Group createGroup(GroupReq request);
    Group updateGroup(Long id, GroupReq request);
    void deleteGroup(Long id);
}
