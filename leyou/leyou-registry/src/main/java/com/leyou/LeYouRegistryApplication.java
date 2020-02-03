package com.leyou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @ClassName LeyouRegistryApplication
 * @Description: 乐优eureka启动类
 * @Author Arthas_liubin@Foxmail.com
 * @Date 2020/2/3 21:17
 * @Version V1.0
 **/
@SpringBootApplication
@EnableEurekaServer
public class LeYouRegistryApplication {

    public static void main(String[] args) {
        SpringApplication.run(LeYouRegistryApplication.class);
    }
}
