package com.predisw.test.practise.re;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.junit.Test;

public class ReMatchT {

    @Test
    public void matchT(){

        String targetedStr = "%d{common.traceId}%";

        String stringPattern =  "(?<=\\{)[^\\}]+";

        String stringPattern2 =  "\\{(.*)\\}";
        Pattern pattern = Pattern.compile(stringPattern);

        Matcher matching = pattern.matcher(targetedStr);

        if(matching.find()){
            System.out.println(matching.group());

        }
    }

    @Test
    public void matchT2(){
        String expireTimeDesc ="currentTime+3000";
        String patternStr = "[0-9]+";
        Pattern pattern = Pattern.compile(patternStr);
        Matcher matching = pattern.matcher(expireTimeDesc);

        if(matching.find()){
            System.out.println(matching.group());
        }
    }


    @Test
    public void matchT3(){
        String expireTimeDesc ="currentTime+3000";

        String output = expireTimeDesc.replaceAll("currentTime" + " *\\+ *", "");

        System.out.println(output);
    }
}
