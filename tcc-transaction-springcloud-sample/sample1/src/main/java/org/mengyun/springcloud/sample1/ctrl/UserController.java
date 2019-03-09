package org.mengyun.springcloud.sample1.ctrl;

import org.mengyun.springcloud.sample1.entity.User;
import org.mengyun.springcloud.sample1.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/userCtrl")
public class UserController {

    @Autowired
    private IUserService userService;

    @RequestMapping(path = "/save")
    public Map<String, Object> save() {
        Map<String, Object> result = new HashMap<>();

        try {
            User user = new User();
            user.setId(UUID.randomUUID().toString());
            user.setName("foo");
            user.setAge(18);
            userService.save(user);
            result.put("status", 0);
            result.put("msg", "success");
        } catch (Exception e) {
            e.printStackTrace();
            result.put("status", 1);
            result.put("msg", "fail");
        }
        return result;
    }
}