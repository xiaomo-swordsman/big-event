package com.xiaomo.common;

public enum WeekDay {
    MONDAY("星期一"),
    TUESDAY("星期二"),
    WEDNESDAY("星期三"),
    THURSDAY("星期四"),
    FRIDAY("星期五"),
    SATURDAY("星期六"),
    SUNDAY("星期日");

    private String day;

    WeekDay(String day){
        this.day = day;
    }

    public String getDay(){
        return day;
    }

    public static void main(String[] args) {
        System.out.println(WeekDay.MONDAY.getDay());
    }

}
