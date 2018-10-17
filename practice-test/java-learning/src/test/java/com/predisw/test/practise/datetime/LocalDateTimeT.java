package com.predisw.test.practise.datetime;

import java.time.LocalDateTime;
import java.time.ZoneId;
import org.assertj.core.api.Assertions;
import org.assertj.core.data.Offset;
import org.junit.Test;

public class LocalDateTimeT {


    @Test
    public void DateTimeToMilliSeconds(){
        LocalDateTime dateTime = LocalDateTime.now().plusSeconds(60L);
        Long millionSeconds = dateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        Assertions.assertThat(millionSeconds).isCloseTo((System.currentTimeMillis()+60*1000), Offset.offset(1000L));
    }

}
