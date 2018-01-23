package com.predisw.test.practise.shell;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * Created by eggnwwg on 1/9/2018.
 */
public class RuntimeT {


    @Test
    public boolean testPipleCmd(){

        int port = 6868;
        int timeoutSeconds =2;

        boolean result = false;

        long begin = System.currentTimeMillis();

        String cmd = "netstat -an |grep "+port;
        try {
            while((System.currentTimeMillis() - begin) < timeoutSeconds*1000 ){

                Process ps =Runtime.getRuntime().exec(new String[] {"/bin/sh","-c",cmd});
                ps.waitFor();

                BufferedReader br = new BufferedReader(new InputStreamReader(ps.getInputStream()));
                StringBuffer sb = new StringBuffer();
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line).append("\n");
                }
                String resultStr = sb.toString();

                if(resultStr.toUpperCase().contains("ESTABLISHED")){
                    result = true;
                    break;
                }

                TimeUnit.SECONDS.sleep(1);

            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if(!result){
            throw new IllegalStateException("The Diameter Service doesn't establish with port: "+port +"in "+timeoutSeconds+" seconds");
        }

        return result;


    }

}
