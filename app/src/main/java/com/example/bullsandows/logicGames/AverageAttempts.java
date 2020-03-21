package com.example.bullsandows.logicGames;


public class AverageAttempts {
    private String stringAverageAttempts;
    private double lastGame;
    private double newAverageAttempts;
    private String stringNewAverageAttempts;


    public AverageAttempts(String averageAttempts, int lastGame) {
        this.stringAverageAttempts = averageAttempts;
        this.lastGame = lastGame;
        average();
    }

    public double getNewAverageAttempts() {
        return newAverageAttempts;
    }

    //рассчитываем среднее число попыток
    void average() {
        double AA;

        AA = Double.valueOf(stringAverageAttempts);
        System.out.println("AA " + AA);
        newAverageAttempts = (AA + lastGame) / 2;
        newAverageAttempts = roundAvoid(newAverageAttempts, 3);

        System.out.println("newAverageAttempts " + newAverageAttempts);
        stringNewAverageAttempts = String.valueOf(newAverageAttempts);
        System.out.println("stringAverageAttempts " + stringAverageAttempts);
    }

    public String getStringNewAverageAttempts() {
        return stringNewAverageAttempts;
    }

    //для округления числе
    private double roundAvoid(double value, int places) {
        double scale = Math.pow(10, places);
        return Math.round(value * scale) / scale;
    }
}
