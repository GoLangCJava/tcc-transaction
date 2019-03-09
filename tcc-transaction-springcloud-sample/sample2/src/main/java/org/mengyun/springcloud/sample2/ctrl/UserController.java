package org.mengyun.springcloud.sample2.ctrl;

import org.mengyun.springcloud.sample2.entity.UserParamWarp;
import org.mengyun.springcloud.sample2.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/userCtrl")
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping(path = "/save")
    public Map<String, Object> save(@RequestBody UserParamWarp userParamWarp) {
        Map<String, Object> result = new HashMap<>();
        try {
            userService.save(userParamWarp.getTransactionContext(), userParamWarp.getUser());
            result.put("status", 0);
            result.put("msg", "success");
        } catch (Exception e) {
            result.put("status", 1);
            result.put("msg", "fail");
        }
        return result;
    }

    @PostMapping(path = "/confirmSave")
    public Map<String, Object> confirmSave(@RequestBody UserParamWarp userParamWarp) {
        Map<String, Object> result = new HashMap<>();
        try {
            userService.confirmSave(userParamWarp.getTransactionContext(), userParamWarp.getUser());
            result.put("status", 0);
            result.put("msg", "success");
        } catch (Exception e) {
            result.put("status", 1);
            result.put("msg", "fail");
        }
        return result;
    }

    @PostMapping(path = "/cancelSave")
    public Map<String, Object> cancelSave(@RequestBody UserParamWarp userParamWarp) {
        Map<String, Object> result = new HashMap<>();
        try {
            userService.cancelSave(userParamWarp.getTransactionContext(), userParamWarp.getUser());
            result.put("status", 0);
            result.put("msg", "success");
        } catch (Exception e) {
            result.put("status", 1);
            result.put("msg", "fail");
        }
        return result;
    }
}