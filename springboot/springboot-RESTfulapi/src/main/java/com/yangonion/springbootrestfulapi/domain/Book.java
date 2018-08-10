package com.yangonion.springbootrestfulapi.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author Yang
 */
@Entity
public class Book {

    public  Book(){

    }

    public Book(String name, Double price, String remark) {
        this.name = name;
        this.price = price;
        this.remark = remark;
    }

    @Id
    @GeneratedValue
    private  Long id;

    @Column(nullable = false)
    private  String name;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = true)
    private  String remark;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }


}
