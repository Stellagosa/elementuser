package com.springboot.elementuser.controller;

import com.springboot.elementuser.entity.User;
import com.springboot.elementuser.service.UserService;
import com.springboot.elementuser.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/queryAll")
    public List<User> queryAll() {
        return userService.queryAll();
    }

    @PostMapping("/save")
    public Result save(@RequestBody User user) {
        Result result = new Result();
        try {
            userService.saveUser(user);
            result.setMsg("添加用户成功！");
        } catch (Exception e) {
            result.setStatus(false)
                  .setMsg("数据库连接失败！");
        }
        return result;
    }

    @PutMapping("/update")
    public Result update(@RequestBody User user) {
        Result result = new Result();
        try {
            userService.updateUser(user);
            result.setMsg("用户信息修改成功！");
        } catch (Exception e) {
            result.setStatus(false)
                  .setMsg("数据库连接失败！");
        }
        return result;
    }

    @DeleteMapping("/delete")
    public Result delete(Integer id) {
        Result result = new Result();
        try {
            userService.deleteById(id);
            result.setMsg("用户信息已删除！");
        } catch (Exception e) {
            result.setStatus(false)
                  .setMsg("数据库连接失败！");
        }
        return result;
    }

    @GetMapping("/queryByPage")
    public Map<String, Object> queryByPage(Integer currentPage, Integer pageSize) {
        List<User> users = userService.queryByPage(currentPage, pageSize);
        Long count = userService.queryCount();

        Map<String, Object> map = new HashMap<>();

        map.put("count", count);
        map.put("users", users);

        return map;
    }

    @GetMapping("/queryById")
    public User queryById(Integer id) {
        return userService.queryById(id);
    }

}
