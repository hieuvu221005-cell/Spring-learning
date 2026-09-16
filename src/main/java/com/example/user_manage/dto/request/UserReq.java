package com.example.user_manage.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserReq implements Serializable {
    private String userName;
    private String firstName;
    private String lastName;
    private Long age;
}
