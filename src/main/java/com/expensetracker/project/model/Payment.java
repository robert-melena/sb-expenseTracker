package com.expensetracker.project.model;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Payment {
    CASH,
    CARD,
    CHECK,
    APPLEPAY;

    //NOTE: Spring uses Jackson to convert json to Java
    @JsonCreator
    public static Payment from(String value){
        return Payment.valueOf(value.toUpperCase());
    }


}
