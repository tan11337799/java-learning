package com.twhupup.entity;

public class Fruit {
    private String name;
    private int price;
    private int number;
    private String des;

    public Fruit(String name, int price, int number, String des) {
        this.name = name;
        this.price = price;
        this.number = number;
        this.des = des;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getNumber() {
        return number;
    }
}
