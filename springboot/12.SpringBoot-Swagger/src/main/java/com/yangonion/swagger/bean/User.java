package com.yangonion.swagger.bean;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class User  implements Serializable {

    private static  final long servialVersionId =-339516038496531943L;

    @ApiModelProperty(value = "主键",required = true)
    private  long id;
    @ApiModelProperty(value = "姓名",required = true)
    private  String name;
    @ApiModelProperty(value = "年龄")
    private  int age;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


        }
