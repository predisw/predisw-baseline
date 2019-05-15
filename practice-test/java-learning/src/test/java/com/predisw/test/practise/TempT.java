package com.predisw.test.practise;

import com.predisw.test.practise.clazz.ClassNewWithOverride;
import org.junit.Test;

import java.lang.reflect.TypeVariable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE_TIME;

/**
 * Created by eggnwwg on 6/12/2017.
 */
public class TempT<T extends MockitoT> {


    public static void main(String [] args){

        String userId = "123456789";
        long id = userId.length() > 8 ? Long.valueOf(userId.substring(8)):Long.valueOf(userId);

        System.out.println(id);


        System.out.println("3168".equals(3168));

        System.out.println(Integer.valueOf(3168).equals(3168));
//
//        System.out.println(Long.MAX_VALUE);
//        System.out.println((long)(Integer.MAX_VALUE)*2);
//
//        TypeVariable[] types =  TempT.class.getTypeParameters();
//
//
//        System.out.println(types.length + " "+types[0].getName());
//
//
//        System.out.println(String.class.getCanonicalName());

    }

    @Test
    public void getTimeStamp() throws ParseException {
        SimpleDateFormat sd = new SimpleDateFormat();
        //sd.setTimeZone(TimeZone.getTimeZone("GMT+08:00"));
        sd.applyPattern("yyyy-MM-dd'T'HH:mm:ss Z");

        Long timestamp = sd.parse("2028-01-31T00:15:00 +0800").getTime();
        Long timestamp2 = sd.parse("2019-10-01T10:31:56 +0800").getTime();
        System.out.println(timestamp);
        System.out.println(timestamp2);

        ZonedDateTime dateTime = ZonedDateTime.of(LocalDateTime.parse("2019-10-01T10:31:56", ISO_LOCAL_DATE_TIME),ZoneId.of("UTC+08:00"));

        System.out.println(dateTime.toInstant().toEpochMilli());

        String dateStr = LocalDateTime.ofInstant(Instant.ofEpochMilli(1832861700000L),ZoneId.of("UTC+00:00")).format(ISO_LOCAL_DATE_TIME);
        System.out.println(dateStr);


    }

    @Test
    public void testAny(){
        System.out.println(String.class.isPrimitive());

        System.out.println("abcde".indexOf("cd"));

    }


    @Test
    public void testNewOverride(){

        ClassNewWithOverride classNewWithOverride = new ClassNewWithOverride("xx");

        ClassNewWithOverride classNewWithOverride2 = new ClassNewWithOverride("xx"){

            @Override
            public void setTest(){
                System.out.println("no-changed");

            }
        };

        System.out.println(classNewWithOverride2.getTest());

    }


}
