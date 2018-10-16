package com.yangonion.exceptiondemo.exceptions;

public class DomainException extends  RuntimeException {
    private  static  final  long serialVersionUID =  -1574716826948451793L;



    private  String id;

    public  DomainException(String id){
        super("订单号不存在！");
        this.id=id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
