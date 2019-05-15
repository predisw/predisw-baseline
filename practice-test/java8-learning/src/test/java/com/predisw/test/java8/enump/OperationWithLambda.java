package com.predisw.test.java8.enump;

import java.util.function.ToDoubleBiFunction;

public enum  OperationWithLambda {

    PLUS("+",(x,y) -> x+y),

    MINUS("-"),
            ;


    private String symbol;

    private ToDoubleBiFunction<Double,Double> function = (x,y) -> x -y;


    OperationWithLambda(String symbol){
        this.symbol = symbol;
    }

    OperationWithLambda(String symbol, ToDoubleBiFunction<Double,Double> function){

        this.symbol = symbol;
        this.function = function;
    }


    public Double apply(double x, double y){
        return function.applyAsDouble(x,y);
    }


    public static void main(String[] args) {
        System.out.println(MINUS.apply(2,1));
        System.out.println(PLUS.apply(2,1));

        System.out.println("the function is instance of object: "+ MINUS.function instanceof Object);
    }




}
