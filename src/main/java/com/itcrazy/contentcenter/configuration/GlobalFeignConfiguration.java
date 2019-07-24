package com.itcrazy.contentcenter.configuration;

import feign.Logger;
import org.springframework.context.annotation.Bean;

/**
 * 这个类不要加@Configuration注解，不然又会出现父子上下文的问题
 */
public class GlobalFeignConfiguration {

    @Bean
    public Logger.Level level(){
        return Logger.Level.FULL;
    }
}
