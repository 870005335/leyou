package com.leyou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @ClassName LeYouGatewayApplication
 * @Description: 乐优Zuul网关启动类
 * @Author Arthas_liubin@Foxmail.com
 * @Date 2020/2/3 21:28
 * @Version V1.0
 **/
@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
public class LeYouGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(LeYouGatewayApplication.class);
    }
}
