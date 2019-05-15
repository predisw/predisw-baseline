package com.predisw.test.java8.enump;

public enum OperationWithDefaultMethod {

    PLUS("+"){
        @Override
        double apply(double x, double y) {
            return x + y;
        }
    },

    MINUS("-"),

    ;


    private String symbol;

    OperationWithDefaultMethod(String symbol){
        this.symbol = symbol;
    }

    double apply(double x, double y){
        return x * y;
    }


}
