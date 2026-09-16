package com.example.user_manage.service.imp;

import com.example.user_manage.dto.request.UserGroupReq;
import com.example.user_manage.entity.Group;
import com.example.user_manage.entity.User;
import com.example.user_manage.entity.UserGroup;
import com.example.user_manage.reponsitory.GroupRepo;
import com.example.user_manage.reponsitory.UserGroupRepo;
import com.example.user_manage.reponsitory.UserRepo;
import com.example.user_manage.service.UserGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserGroupServiceImp implements UserGroupService {


    private final UserGroupRepo userGroupRepo;
    private final GroupRepo groupRepo;
     private final UserRepo userRepo;


    @Override
    public String addUserToGroup(Long userId,Long groupId) {

        Optional<Group> groupOptional = groupRepo.findById(groupId);
        if(groupOptional.isEmpty()){
            throw new RuntimeException("id group khong ton tai");
        }
        Group group = groupOptional.get();

        Optional<User> userOptional = userRepo.findById(userId);
        if (userOptional.isEmpty()) {
            throw new RuntimeException("id user khong ton tai");
        }
        User user = userOptional.get();

        if(userGroupRepo.findByUserIdAndGroupId(userId,groupId).isPresent()){
            return "User da ton tai trong group";
        }

        UserGroup userGroup = new UserGroup();
        userGroup.setUser(user);
        userGroup.setGroup(group);

        userGroupRepo.save(userGroup);
        return "Them user vao group thanh cong";
    }

    @Override
    public String addUsersToGroup(Long groupId, UserGroupReq request) {
        Optional<Group> groupOptional = groupRepo.findById(groupId);
        if(groupOptional.isEmpty()){
            throw new RuntimeException("id group khong ton tai");
        }
        Group group = groupOptional.get();

        List<User> users = userRepo.findAllById(request.getUserIds());
        if(users.isEmpty()){
            throw new RuntimeException("user id khong ton tai");
        }
        List<UserGroup> userGroups = new ArrayList<>();
        for(int i = 0;i < users.size();i++){
            if(userGroupRepo.findByUserIdAndGroupId(users.get(i).getId(),groupId).isPresent()){
                continue;
            }
            UserGroup userGroup = new UserGroup();
            userGroup.setGroup(group);
            userGroup.setUser(users.get(i));

            userGroups.add(userGroup);
        }

        userGroupRepo.saveAll(userGroups);

        return "Them cac user thanh cong";
    }

    @Override
    public void removeUserToGroup(Long userId, Long groupId) {
        Optional<UserGroup> userGroupOptional = userGroupRepo.findByUserIdAndGroupId(userId,groupId);
      if(userGroupOptional.isEmpty()){
          throw new RuntimeException("user khong ton tai trong group");
      }

      UserGroup userGroup = userGroupOptional.get();
      userGroupRepo.delete(userGroup);
    }
}
