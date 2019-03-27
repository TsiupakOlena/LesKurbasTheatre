package com.leskurbas.dto;

public class MoneyConvertor {

    private static final String CURRENCY = "UAH";

    public static String convertMoneyToString(float f) {
        String money = String.valueOf(f);
        int dotIndex = money.indexOf('.');
        int length = money.length();

        if (dotIndex + 1 == length) {
            money += "0";
        } else if(dotIndex + 2 < length) {
            money = money.substring(0, money.indexOf('.') + 3);
        }
        money = money.replace('.', ',');
        money += " " + CURRENCY;
        return money;
    }
}
