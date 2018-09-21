package com.yangonion.config.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@Configuration
@PropertySource("classpath:personal.properties")
public class PersonalBean {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Value("${test.name}")
    private  String name;

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Value("${test.sex}")
    private  String sex;

    @Override
    public String toString() {
        return "PersonalBean{" +
                "name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }
}
