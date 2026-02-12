package com.expensetracker.project.model;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Category {
    TRAVEL,
    FOOD,
    ENTERTAINMENT,
    HOUSING,
    MEDIA,
    MISC;

    //from Jackson, which is a libary used for JSON
    /*
    * when Jackson attempts to deserialize JSON into a Category enum, will look for method annotated with @JsonCreator
    * Tells Jackson to use this method to convert JSON string into a category enum*/
    @JsonCreator
    public static Category from(String value){
        return Category.valueOf(value.toUpperCase());
    }
}
