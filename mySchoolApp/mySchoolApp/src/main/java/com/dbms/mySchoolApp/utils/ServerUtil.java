package com.dbms.mySchoolApp.utils;

import org.springframework.stereotype.Component;

@Component
public class ServerUtil {
    public String getHostAddressAndPort() {
        String hostname = System.getenv("SERVER_HOSTNAME");
        if (hostname == null) {
            return "https://my-school-application.herokuapp.com";
        } else {
            return hostname;
        }
    }
}
