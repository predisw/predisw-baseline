package com.predisw.https;

import java.util.HashMap;
import java.util.Map;

public class HttpSender {
    private static final String JVM_SSL_TRUSTSTORE_KEY="javax.net.ssl.trustStore";
    private static final String JVM_SSL_TRUSTSTORE_PASSWORD_KEY="javax.net.ssl.trustStorePassword";

    private static final String JVM_SSL_TRUSTSTORE="/etc/modules/DP-SCEF-Monitor-Traffic/cert/jvm.truststore";
    private static final String JVM_SSL_TRUSTSTORE_PASSWORD="scef@ece";

    public static void main(String[] args) {

        System.setProperty(JVM_SSL_TRUSTSTORE_KEY,JVM_SSL_TRUSTSTORE);
        System.setProperty(JVM_SSL_TRUSTSTORE_PASSWORD_KEY,JVM_SSL_TRUSTSTORE_PASSWORD);

        String url = "https://MONTE-TG:8443/event_report";
        Map<String,String> content = new HashMap<>();
        content.put("test","test");
        HttpUtil.doPost(url,content);
    }

}
