package com.leyou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @ClassName LeYouUploadApplication
 * @Description: 乐优上传微服务引导类
 * @Author Arthas_liubin@Foxmail.com
 * @Date 2020/2/11 21:47
 * @Version V1.0
 **/
@SpringBootApplication
@EnableDiscoveryClient
public class LeYouUploadApplication {

    public static void main(String[] args) {
        SpringApplication.run(LeYouUploadApplication.class);
    }
}
