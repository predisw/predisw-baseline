package com.predisw.test.practise.datetime;

import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class FormatT {


    @Test
    public void SimpleDateFormatT() throws ParseException {
        SimpleDateFormat sd = new SimpleDateFormat();
        //sd.applyPattern("yyyy-MM-DD HH:mm:ss X"); // can not parse the string without timeZone info

        sd.applyPattern("yyyy-MM-DD HH:mm:ss");  // can parse the string with timeZone info

        String timeStr_noTZ = "2018-09-01 23:32:10";
        String timeStr_TZ07 = "2018-09-01 23:32:10 +07:00";
        String timeStr_TZ09 = "2018-09-01 23:32:10 +09:00";

        Date time_noTZ = sd.parse(timeStr_noTZ);
        Date time_TZ07 = sd.parse(timeStr_TZ07);
        Date time_TZ09 = sd.parse(timeStr_TZ09);

        Assert.assertTrue(time_noTZ.compareTo(time_TZ07) == 0);
        Assert.assertTrue(time_TZ07.equals(time_TZ09));

        System.out.println(time_noTZ.getTime());
        System.out.println(time_TZ07);
        System.out.println(time_TZ09);



    }


    @Test
    public void OffsetDateTimeComparation() {

        String timeStr1 = "2018-01-31T13:30:00+07:00";
        String timeStr2 = "2018-01-31T14:30:00+08:00";
        String timeStr_TZ07 = "2018-01-31T13:30:00+07:00";
        OffsetDateTime time1 = OffsetDateTime.parse(timeStr1, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        OffsetDateTime time2 = OffsetDateTime.parse(timeStr2, DateTimeFormatter.ISO_OFFSET_DATE_TIME);

        Assert.assertFalse("OffsetDateTime can not compare with equal", time1.equals(time2));

        Assert.assertTrue("OffsetDateTime should compare with isEqual", time1.isEqual(time2));

        System.out.println(OffsetDateTime.parse(timeStr_TZ07, DateTimeFormatter.ISO_OFFSET_DATE_TIME).toInstant().toEpochMilli());



    }


}
