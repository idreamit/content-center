package com.itcrazy.contentcenter.feignclient.fallbackfactory;


import com.itcrazy.contentcenter.domain.dto.user.UserDTO;
import com.itcrazy.contentcenter.feignclient.UserCenterFeignClient;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UserCenterFeignClientFallbackFactory implements FallbackFactory<UserCenterFeignClient> {
    @Override
    public UserCenterFeignClient create(Throwable cause) {
        return new UserCenterFeignClient() {
            @Override
            public UserDTO findById(Integer id) {
                log.warn("远程调用被限流/降级了",cause);
                UserDTO userDTO=new UserDTO();
                userDTO.setWxNickname("流控/降级返回的用户");
                return userDTO;
            }
        };
    }
}
