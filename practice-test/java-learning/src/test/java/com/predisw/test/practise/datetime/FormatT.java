package com.predisw.test.practise.datetime;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class FormatT {


    @Test
    public void SimpleDateFormatT() throws ParseException {

        SimpleDateFormat sd = new SimpleDateFormat();
        sd.applyPattern("yyyy-MM-DD HH:mm:ss X");

        sd.applyPattern("yyyy-MM-DD HH:mm:ss");

        String time1 = "2018-09-01 23:32:10";

        String time2 = "2018-09-01 23:32:10 +07:00";

        System.out.println(sd.parse(time1));

        System.out.println(sd.parse(time2));



    }
}
