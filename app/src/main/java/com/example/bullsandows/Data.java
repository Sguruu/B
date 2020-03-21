package com.example.bullsandows;

import java.util.ArrayList;
import java.util.List;

public class Data {
    private static String emailUser = "";
    public static String stringAverageResult;
    public static List<Model> modelList = new ArrayList<>();

    public static String getEmailUser() {
        return emailUser;
    }

    public static void setEmailUser(String emailUser) {
        Data.emailUser = emailUser;
    }

    public static void setEmailAut(String emailUser) {
        Data.emailUser = emailUser;
    }
}
