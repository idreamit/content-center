package com.itcrazy.contentcenter.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name="baidu",url = "http://www.baidu.com")
public interface TestBaiduFeignClient {
    @GetMapping("")
    String index();
}
