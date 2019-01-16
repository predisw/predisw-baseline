package com.predisw.test.java8.time;

import org.junit.Test;

import java.rmi.registry.LocateRegistry;
import java.time.LocalDate;

public class LocalDateT {


    @Test
    public void MonthMinusT(){

        LocalDate now = LocalDate.now();

        LocalDate DateOfTwoYearsAgo = now.minusMonths(24); // two years ago

        LocalDate firstDayOfMonth = DateOfTwoYearsAgo.withDayOfMonth(1);
        LocalDate LastDayOfMonth =  DateOfTwoYearsAgo.withDayOfMonth(31);

        System.out.println(">>>>>>>firstDayOfMonth "+firstDayOfMonth);
        System.out.println(">>>>>>> LastDayOfMonth "+LastDayOfMonth);

        System.out.println(">>>>>>>>>DateOfTwoYearsAgo "+DateOfTwoYearsAgo);



    }


}
