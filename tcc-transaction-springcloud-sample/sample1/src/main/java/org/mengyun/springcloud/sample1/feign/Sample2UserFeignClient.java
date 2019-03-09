package org.mengyun.springcloud.sample1.feign;

import org.mengyun.springcloud.sample1.entity.UserParamWarp;
import org.mengyun.tcctransaction.api.Compensable;
import org.mengyun.tcctransaction.context.AwareTransactionContextEditor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient("sample2")
public interface Sample2UserFeignClient {

    @Compensable(confirmMethod = "confirmSave", cancelMethod = "cancelSave",
            transactionContextEditor = AwareTransactionContextEditor.class)
    @PostMapping(path = "/userCtrl/save", produces = "application/json;charset=UTF-8")
    Map<String, Object> save(@RequestBody UserParamWarp userParamWarp);

    @PostMapping(path = "/userCtrl/confirmSave", produces = "application/json;charset=UTF-8")
    Map<String, Object> confirmSave(@RequestBody UserParamWarp userParamWarp);

    @PostMapping(path = "/userCtrl/cancelSave", produces = "application/json;charset=UTF-8")
    Map<String, Object> cancelSave(@RequestBody UserParamWarp userParamWarp);
}