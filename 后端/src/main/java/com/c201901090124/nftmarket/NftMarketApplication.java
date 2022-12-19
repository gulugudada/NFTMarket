package com.c201901090124.nftmarket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling  //定时任务或者周期任务的注解
public class NftMarketApplication {

    public static void main(String[] args) {
        SpringApplication.run(NftMarketApplication.class, args);
    }

}
