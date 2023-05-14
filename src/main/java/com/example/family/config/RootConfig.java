package com.example.family.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.List;

@Configuration
@ComponentScan(basePackages = "com.example.family", excludeFilters = @ComponentScan.Filter(Controller.class))
public class RootConfig {

    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/nation?serverTimezone=Asia/Seoul");
        dataSource.setUsername("root");
        dataSource.setPassword("1447");

        dataSource.setInitialSize(10);
        dataSource.setMaxTotal(10);
        dataSource.setMinIdle(10);
        dataSource.setMaxIdle(10);

        dataSource.setMaxWaitMillis(1000);

        dataSource.setTestOnBorrow(true);
        dataSource.setTestOnReturn(true);
        dataSource.setTestWhileIdle(true);

        return dataSource;
    }

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        //pretty print json
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        //value -> null 무시
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        //LocalDate, LocalDateTime support jsr310
        objectMapper.registerModule(new JavaTimeModule());
        //timestamp 출력을 disable. -> 문자열형태로 출력
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return objectMapper;
    }
}
