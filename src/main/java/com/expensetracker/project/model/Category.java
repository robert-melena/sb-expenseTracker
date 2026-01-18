package com.expensetracker.project.model;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Category {
    TRAVEL,
    FOOD,
    ENTERTAINMENT,
    HOUSING,
    MEDIA,
    MISC;

    @JsonCreator
    public static Category from(String value){
        return Category.valueOf(value.toUpperCase());
    }
}
