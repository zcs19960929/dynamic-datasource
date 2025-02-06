package com.example.dynamicdatasource;

import com.example.dynamicdatasource.config.StartFailedEvent;
import com.example.dynamicdatasource.constant.ServerInfoConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@Slf4j
@SpringBootApplication(scanBasePackages = {"com.example.dynamicdatasource"})
public class DynamicDatasourceApplication {

    public static void main(String[] args) {

        SpringApplication springApplication = new SpringApplicationBuilder(DynamicDatasourceApplication.class).build();
        springApplication.addListeners(new StartFailedEvent());
        springApplication.run(args);

        log.info("=============================spring boot start successful ! {}=============================", ServerInfoConstants.SERVER_HOSTNAME);
    }

}
