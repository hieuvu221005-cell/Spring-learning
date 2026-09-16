package com.example.user_manage.service;

import com.example.user_manage.dto.request.UserGroupReq;

public interface UserGroupService {
      String addUserToGroup(Long userId,Long groupId);
      void removeUserToGroup(Long userId,Long groupId);
      String addUsersToGroup(Long groupId, UserGroupReq request);
}
