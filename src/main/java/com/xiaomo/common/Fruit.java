package com.xiaomo.common;

public enum Fruit {
    APPLE(10,"苹果",5.00),
    BANANA(7,"香蕉",7.00),
    ORANGE(5,"橘子",12.00);

    private int num;
    private String category;
    private double price;

    Fruit(int num, String category, double price) {
        this.num = num;
        this.category = category;
        this.price = price;
    }

    public static void main(String[] args) {
        System.out.println(Fruit.APPLE);
        System.out.println(Fruit.APPLE.num);
        System.out.println(Fruit.APPLE.category);
    }

}
