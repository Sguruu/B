package com.example.bullsandows;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bullsandows.adapter.AdapterHistory;
import com.example.bullsandows.logicGames.AverageAttempts;
import com.example.bullsandows.logicGames.BullsOws;
import com.example.bullsandows.logicGames.MakeANumber;


import java.util.ArrayList;
import java.util.List;

public class The_Game extends AppCompatActivity {
    private Button GameNumberButton, gb1, gb2, gb3, gb4, gb5, gb6, gb7, gb8, gb9, DialogButtCon,
            DialogButtExit;
    private String toPress = "", resultUser;
    private TextView TVtoPress, TVResult;
    private int ClikButt, attempts;
    private RecyclerView recyclerViewRating;
    private static AdapterHistory adapterHistory;
    private List<GameModel> history = new ArrayList<>();
    private boolean GameEnd = true;
    MakeANumber makeANumber = new MakeANumber();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.the_game);


        //==========инициализация==========
        GameNumberButton = findViewById(R.id.GameNumberButton);
        gb1 = findViewById(R.id.Gamebutton1);
        gb2 = findViewById(R.id.Gamebutton2);
        gb3 = findViewById(R.id.Gamebutton3);
        gb4 = findViewById(R.id.Gamebutton4);
        gb5 = findViewById(R.id.Gamebutton5);
        gb6 = findViewById(R.id.Gamebutton6);
        gb7 = findViewById(R.id.Gamebutton7);
        gb8 = findViewById(R.id.Gamebutton8);
        gb9 = findViewById(R.id.Gamebutton9);
        //Dialog
        final Dialog dialog = new Dialog(The_Game.this);

        dialog.setContentView(R.layout.dialog);
        dialog.setCancelable(false); //не кликабельная область вокруг диалога
        dialog.setTitle("Тест диалога");
        //


        DialogButtCon = dialog.findViewById(R.id.DialogButtonGameNow);
        DialogButtExit = dialog.findViewById(R.id.DialogButtonMenu);

        TVtoPress = findViewById(R.id.GametextViewToPress);
        TVResult = findViewById(R.id.GametextViewRsult);

        GameNumberButton.setEnabled(false);
        GameNumberButton.setText("Угадать");
        TVtoPress.setText("");
        gb1.setText("1");
        gb2.setText("2");
        gb3.setText("3");
        gb4.setText("4");
        gb5.setText("5");
        gb6.setText("6");
        gb7.setText("7");
        gb8.setText("8");
        gb9.setText("9");


        //====================
        //==========recycler==========
        recyclerViewRating = findViewById(R.id.Gamerecycler);
        recyclerViewRating.setHasFixedSize(true);
        //отвечает за форму отображения элемента
        recyclerViewRating.setLayoutManager(new LinearLayoutManager(this));
        //====================


        //==========Обработчик нажатия кнопок==========
        gb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ButtonLogic(v, 1);
            }
        });
        gb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ButtonLogic(v, 2);
            }
        });
        gb3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ButtonLogic(v, 3);
            }
        });
        gb4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ButtonLogic(v, 4);
            }
        });
        gb5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ButtonLogic(v, 5);
            }
        });
        gb6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ButtonLogic(v, 6);
            }
        });
        gb7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ButtonLogic(v, 7);
            }
        });
        gb8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ButtonLogic(v, 8);
            }
        });
        gb9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ButtonLogic(v, 9);
            }
        });

        GameNumberButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BullsOws bullsOws = new BullsOws();
                //метод отбравки данных логике игры
                //и вычисление результата
                //1. Отправить данные

                bullsOws.getUser(toPress);
                //2. Сгенерировать данные
                if (GameEnd) {
                    makeANumber.NumberCreation();
                    GameEnd = false;
                }
                //3. Вычилсть быков
                resultUser = bullsOws.getBulls(makeANumber.getNumber()) + "Б";
                //4. Вычислить коров
                resultUser = resultUser + bullsOws.getOws(makeANumber.getNumber()) + "К";
                TVResult.setText(resultUser);


                //загрузка данных в историю
                GameModel gameModel = new GameModel(toPress, resultUser);
                history.add(gameModel);

                //adapter
                adapterHistory = new AdapterHistory(history);
                //set adapter to recyclerview
                recyclerViewRating.setAdapter(adapterHistory);
                //====================
                startDialog(dialog, bullsOws.getBuInt());


            }
        });

        DialogButtCon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
            }
        });

        DialogButtExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                startActivity(new Intent(The_Game.this, Main_Menu.class));
            }
        });

        //====================


    }

    private void setTvtoPress() {
        TVtoPress.setText(toPress);
        if (ClikButt == 4) {
            enableButton(false);
            GameNumberButton.setEnabled(true);

        }


    }

    //логика нажатия кнопки
    private void ButtonLogic(View v, int i) {
        ClikButt += 1;
        if (ClikButt <= 4) {
            toPress = toPress + i;
            setTvtoPress();
            v.setEnabled(false);
        }

    }

    //активность кнопки
    private void enableButton(boolean f) {
        if (f) {
            gb1.setEnabled(true);
            gb2.setEnabled(true);
            gb3.setEnabled(true);
            gb4.setEnabled(true);
            gb5.setEnabled(true);
            gb6.setEnabled(true);
            gb7.setEnabled(true);
            gb8.setEnabled(true);
            gb9.setEnabled(true);
            GameNumberButton.setEnabled(false);
        } else {
            gb1.setEnabled(false);
            gb2.setEnabled(false);
            gb3.setEnabled(false);
            gb4.setEnabled(false);
            gb5.setEnabled(false);
            gb6.setEnabled(false);
            gb7.setEnabled(false);
            gb8.setEnabled(false);
            gb9.setEnabled(false);
            GameNumberButton.setEnabled(false);
        }


    }

    private void restartDialog(Dialog dialog) {
        //Обнуление всех данных
        System.out.println("JJJJJJJОООБНУЛЯЙ");
        System.out.println("=================   Среднее" + Data.stringAverageResult + " " + attempts);
        AverageAttempts averageAttempts = new AverageAttempts(Data.stringAverageResult, attempts);
        FireBase.uploadDataBase(averageAttempts.getNewAverageAttempts(), averageAttempts.getStringNewAverageAttempts());
        FireBase.showStringAverageResult(Data.getEmailUser());
        FireBase.showDate();
        GameEnd = true;
        resultUser = "";
        ClikButt = 0;
        toPress = "";
        attempts = 0;
        TVResult.setText(resultUser);
        TVtoPress.setText(toPress);
        enableButton(true);
        history.clear(); //очистка списка
        adapterHistory.clearItems(); //очистка списка адаптера
        //вызов диалога

        dialog.show();

    }

    private void startDialog(Dialog dialog, int i) {
        if (i != 4) {
            System.out.println("JJJJJJJОООБНУЛЯЙ " + i);
            attempts += 1;
            ClikButt = 0;
            toPress = "";
            TVtoPress.setText(toPress);
            resultUser = "";
            enableButton(true);
        } else restartDialog(dialog);
    }

    public class GameModel {
        private String history;
        private String resultUser;

        public String getResultUser() {
            return resultUser;
        }

        public void setResultUser(String resultUser) {
            this.resultUser = resultUser;
        }

        public GameModel(String h, String result) {
            history = h;
            resultUser = result;
        }

        public String getHistory() {
            return history;
        }

        public void setHistory(String history) {
            this.history = history;
        }
    }
}
