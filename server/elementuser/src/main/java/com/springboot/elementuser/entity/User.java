package com.springboot.elementuser.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDate;

@Data
@Accessors(chain = true)    
public class User {
    private Integer id;
    private String username;
    private String sex;
    private LocalDate birthday;
    private String address;
}
