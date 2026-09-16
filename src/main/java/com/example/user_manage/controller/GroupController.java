package com.example.user_manage.controller;


import com.example.user_manage.dto.request.GroupReq;
import com.example.user_manage.entity.Group;
import com.example.user_manage.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/groups")
public class GroupController {

        private final GroupService groupService;
        //Read
        @GetMapping
        public ResponseEntity<List<Group>> getAll(){
            return ResponseEntity.ok(groupService.getAll());
        }
        //Create
        @PostMapping
        public ResponseEntity<Group> create(@RequestBody GroupReq request){
            Group group = groupService.createGroup(request);
            return ResponseEntity.ok(group);
        }
        //Update
        @PutMapping("/{id}")
        public ResponseEntity<Group> update(@PathVariable Long id,@RequestBody GroupReq request){
            Group groups = groupService.updateGroup(id,request);
            return  ResponseEntity.ok(groups);
        }
        //Delete
        @DeleteMapping("/{id}")
        public void delete(@PathVariable Long id){
            groupService.deleteGroup(id);
        }
}


