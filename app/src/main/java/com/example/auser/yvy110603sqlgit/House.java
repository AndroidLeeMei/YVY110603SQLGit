package com.example.auser.yvy110603sqlgit;

/**
 * Created by auser on 2017/11/8.
 */

public class House {
    public String username,addr;
    public int id,price;
    public House(int id,String username,String addr,int price){
        this.id=id;
        this.username=username;
        this.addr=addr;
        this.price=price;
    }
}
