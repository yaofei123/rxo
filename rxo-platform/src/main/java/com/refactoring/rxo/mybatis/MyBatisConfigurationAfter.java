package com.refactoring.rxo.mybatis;

import com.baomidou.mybatisplus.spring.boot.starter.MybatisPlusAutoConfiguration;
import com.baomidou.mybatisplus.spring.boot.starter.MybatisPlusProperties;
import org.apache.ibatis.type.JdbcType;
import org.joda.time.DateTime;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@AutoConfigureBefore(value = MybatisPlusAutoConfiguration.class)
public class MyBatisConfigurationAfter {

    @Bean
    public JodaDateTimeHandler jodaDateTimeHandler() {
        return new JodaDateTimeHandler();
    }

    @Bean
    @Primary
    public MybatisPlusProperties mybatisProperties(MybatisPlusProperties properties, JodaDateTimeHandler dateTimeTypeHandler) {

        properties.getConfiguration().getTypeHandlerRegistry().register(JdbcType.TIMESTAMP, dateTimeTypeHandler);
        properties.getConfiguration().getTypeHandlerRegistry().register(DateTime.class, dateTimeTypeHandler);
//        try {
//            properties.getConfiguration().getTypeHandlerRegistry().register("java.sql.Timestamp", "com.jzt.demo.DateTimeTypeHandler");
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
        return properties;
    }
}
