package com.example.user_manage.controller;

import com.example.user_manage.dto.request.UserGroupReq;
import com.example.user_manage.service.UserGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users-groups")
public class UserGroupController {
    private final UserGroupService userGroupService;

// them 1 user vào group
    @PostMapping("/user/{userId}/group/{groupId}")
    public ResponseEntity<String> addUserToGroup(@PathVariable Long userId,@PathVariable Long groupId){
       String result =  userGroupService.addUserToGroup(userId,groupId);
        return ResponseEntity.ok(result);
    }

    // them nhieu user vao group
    @PostMapping("/users/group/{groupId}")
    public ResponseEntity<String> addUsersToGroup(@PathVariable Long groupId, @RequestBody UserGroupReq request) {

        String result = userGroupService.addUsersToGroup(groupId, request);

        return ResponseEntity.ok(result);
    }
    //xoa qhe user group
    @DeleteMapping("/user/{userId}/group/{groupId}")
    public ResponseEntity<String> removeUserToGroup(@PathVariable Long userId,@PathVariable Long groupId){
        userGroupService.removeUserToGroup(userId,groupId);
        return ResponseEntity.ok("Xoa thanh cong");
    }
}
