package com.springboot.elementuser;

import com.springboot.elementuser.entity.User;
import com.springboot.elementuser.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
class ElementuserApplicationTests {

    @Autowired
    private UserService userService;

    @Test
    void contextLoads() {
        for (int i = 1; i < 51; i++) {
            User user = new User();
            user.setUsername("张三" + i).setSex("男").setBirthday(LocalDate.of(1995, 5, 27))
                    .setAddress("成都春熙路");
            userService.saveUser(user);
        }
    }

}
