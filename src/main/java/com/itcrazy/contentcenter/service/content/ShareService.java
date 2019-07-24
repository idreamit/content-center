package com.itcrazy.contentcenter.service.content;

import com.itcrazy.contentcenter.dao.content.ShareMapper;
import com.itcrazy.contentcenter.domain.dto.content.ShareDTO;
import com.itcrazy.contentcenter.domain.dto.user.UserDTO;
import com.itcrazy.contentcenter.domain.entity.content.Share;
import com.itcrazy.contentcenter.feignclient.UserCenterFeignClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ShareService {

    private final ShareMapper shareMapper;

    //    private final RestTemplate restTemplate;
    private final UserCenterFeignClient userCenterFeignClient;

//    private final DiscoveryClient discoveryClient;

    public ShareDTO findById(Integer id) {
        //获取分享详情
        Share share = shareMapper.selectByPrimaryKey(id);

        //发布人id
        Integer userId = share.getUserId();

//        List<ServiceInstance> instances = discoveryClient.getInstances("user-center");

        /**
         * JDK 8新特性
         * stream
         * lambda表达式
         * functional  --->  函数式编程
         */
        //所有用户中心实例的请求地址
   /*     List<String> targerURLs=instances.stream()
                //数据变换
                .map(instance-> instance.getUri().toString()+"users/{id}")
                .collect(Collectors.toList());

        //随机算出一个下标
        int i= ThreadLocalRandom.current().nextInt(targerURLs.size());

        //怎样调用用户微服务的/users/{userId}???
        String targerURL=targerURLs.get(i);*/

//        UserDTO userDTO = this.restTemplate.getForObject("http://user-center/users/{userId}", UserDTO.class, userId);

        UserDTO userDTO = this.userCenterFeignClient.findById(userId);
//        log.info("请求的目标地址:{}",targerURL);

        //消息装配
        ShareDTO shareDTO = new ShareDTO();

        BeanUtils.copyProperties(share, shareDTO);

        shareDTO.setWxNickname(userDTO.getWxNickname());

        return shareDTO;
    }

    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        //使用HTTP GET方法去请求，并且返回一个对象
//        String forObject=restTemplate.getForObject("http://localhost:8080/users/1",String.class);
        ResponseEntity<String> forEntity = restTemplate.getForEntity("http://localhost:8080/users/{id}", String.class, 1);
        System.out.println(forEntity.getBody());

        System.out.println(forEntity.getStatusCode());
    }
}
