package com.example.bullsandows.logicGames;

//Логика определения ответа пользователя
//Функция проверки на быков
//Функция проверки на коров, с учетом прошлого результата проверки на быков
public class BullsOws {
    private int[] userResponse = new int[4];
    private int[] getUserResponseTrue = {0, 0, 0, 0};
    private int Bulls;
    private int Ows;

    public int getBuInt() {
        return Bulls;
    }

    //Принимает строковый результат
    public void getUser(String Response) {
        int intResponse = Integer.parseInt(Response);
        //делим число на цифры
        userResponse[3] = intResponse % 10; //1234 =4
        intResponse = intResponse / 10;//1234 = 123
        userResponse[2] = intResponse % 10; //123 = 3
        intResponse = intResponse / 10; // 123 = 12
        userResponse[1] = intResponse % 10; //12 = 2
        userResponse[0] = intResponse / 10; //12 = 1
    }

    //Вычисляет сколько быков принимает загаданное число от пк
    public int getBulls(int[] numberPC) {
        for (int i = 0; i <= 3; i++) {
            if (numberPC[i] == userResponse[i]) {
                Bulls += 1;
                getUserResponseTrue[i] = 1;
            }
        }

        return Bulls;
    }

    //Вычисляет сколько коров, принимает загаданное число от ПК
    public int getOws(int[] numberPC) {
        for (int i = 0; i <= 3; i++) {
            if (getUserResponseTrue[i] != 1) {
                for (int j = 0; j <= 3; j++) {
                    if (numberPC[j] == userResponse[i]) {
                        Ows += 1;
                    }
                }
            }

        }

        return Ows;
    }
}
