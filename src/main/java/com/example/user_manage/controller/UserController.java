package com.example.user_manage.controller;


import com.example.user_manage.dto.request.UserReq;
import com.example.user_manage.entity.User;
import com.example.user_manage.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.autoconfigure.WebMvcProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;
    //Read
    @GetMapping
    public ResponseEntity<List<User>> getAll(){
        return ResponseEntity.ok(userService.getAll());
    }
    //Create
    @PostMapping
    public ResponseEntity<User> create(@RequestBody UserReq request){
        User user = userService.createUser(request);
        return ResponseEntity.ok(user);
    }
    //Update
    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable Long id,@RequestBody UserReq request){
        User users = userService.updateUser(id,request);
        return  ResponseEntity.ok(users);
    }
    //Delete
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        userService.deleteUser(id);
    }
    //lay ra du lieu dong
    @GetMapping("/filter")
    public ResponseEntity<List<User>> filter(@RequestParam(name = "userName",required = false) String userName,
                                             @RequestParam(name = "firstName",required = false) String firstName,
                                             @RequestParam(name = "lastName",required = false) String lastName,
                                             @RequestParam(name = "minAge",required = false) Long minAge,
                                             @RequestParam(name = "maxAge",required = false) Long maxAge){
        List<User> userList = userService.filter(userName,firstName,lastName,minAge,maxAge);
        return ResponseEntity.ok(userList);
    }


    //luyen tap
    @PutMapping("/update-by-firstname")
    public ResponseEntity<String> updateUserByFirstName() {
        userService.updateUserByFirstName();
        return ResponseEntity.ok("Update user thanh cong");
    }

    @DeleteMapping("/delete-by-firstname")
    public ResponseEntity<String> deleteUserByFirstName() {
        userService.deleteUserByFirstName();
        return ResponseEntity.ok("Delete user thanh cong");
    }
}
