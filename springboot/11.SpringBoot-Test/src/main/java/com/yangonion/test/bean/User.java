package com.yangonion.test.bean;

import java.io.Serializable;

public class User  implements Serializable {

    private static  final long servialVersionId =-339516038496531943L;

    private  long id;
    private  String name;

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

    private  int age;
}
