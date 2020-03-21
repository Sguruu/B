package com.example.bullsandows;

public class Model {
    private String id, userID;
    private String stringAverageResult;
    private int averageResult;


    public Model(String id, String userID, String averageResult) {
        this.id = id;
        this.userID = userID;
        this.stringAverageResult = averageResult;
    }

    public Model(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getStringAverageResult() {
        return stringAverageResult;
    }

    public void setStringAverageResult(String stringAverageResult) {
        this.stringAverageResult = stringAverageResult;
    }

    public int getAverageResult() {
        return averageResult;
    }

    public void setAverageResult(int averageResult) {
        this.averageResult = averageResult;
    }
}
