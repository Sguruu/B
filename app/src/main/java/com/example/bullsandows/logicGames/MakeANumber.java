package com.example.bullsandows.logicGames;
//класс созадет произвольное 4 рех значное число
//цифры загаданного числа не повторяются
public class MakeANumber {
    private int number[] = new int[4]; //4 числа
    private int buf;
    private int counter = 0;


    private void Generation() {
        double d = 1 + Math.random() * 9;
        buf = (int) d;
        System.out.println(" buf " + buf);


    }

    //функция замены и генерации новых чисел
    public void NumberCreation() {
        nullNumber();
        for (int i = 0; i < 4; i++) {
            Generation();
            number[counter] = buf;
            if (counter != 0) {
                for (int j = 0; j < counter; j++) {
                    if (number[counter] == number[j]) {
                        Generation();
                        System.out.println("Похожее число " + number[counter] + " . Замена " + buf);
                        number[counter] = buf;
                        j = 0;
                    }
                }
            }
            counter += 1;

        }
    }

    public int[] getNumber() {
        System.out.println(number[0] + "================");
        System.out.println(number[1] + "================");
        System.out.println(number[2] + "================");
        System.out.println(number[3] + "================");
        System.out.println("%%================" + 2322 / 1000);

        return number;
    }

    private void nullNumber() {
        counter = 0;
        for (int i = 0; i < 4; i++) {
            number[i] = 0;
        }
    }
}
