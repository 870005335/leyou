package com.leyou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @ClassName LeyouItemServiceApplication
 * @Description: 乐优商品微服务启动类
 * @Author Arthas_liubin@Foxmail.com
 * @Date 2020/2/3 21:39
 * @Version V1.0
 **/
@SpringBootApplication
@EnableDiscoveryClient
public class LeYouItemServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(LeYouItemServiceApplication.class);
    }
}
