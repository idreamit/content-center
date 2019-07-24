package com.itcrazy.contentcenter.feignclient;

import com.itcrazy.contentcenter.domain.dto.user.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(name="user-center",configuration = GlobalFeignConfiguration.class)
@FeignClient(name="user-center")
public interface UserCenterFeignClient {
    /**
     * 这边其实用的是Spring MVC的注解
     * 当findById()方法被调用的时候
     * 会构造出这样的url:http://user-center/users/{id}，并且去请求
     * 然后把响应的结果转换给UserDTO即可
     * @param id
     * @return
     */
    @GetMapping("/users/{id}")
    UserDTO findById(@PathVariable Integer id);
}
