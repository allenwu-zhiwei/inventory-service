package com.nusiss.inventoryservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "user-service",contextId="userfeignzm2")
public interface UserClient {

    @GetMapping("/users/queryCurrentUser")
    String queryCurrentUser();
}
