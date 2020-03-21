package com.example.bullsandows;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bullsandows.adapter.AAdapter;
import com.example.bullsandows.logicGames.MakeANumber;

public class Main_Menu extends AppCompatActivity {
    private final String TAG = "Main_Menu";
    private RecyclerView recyclerViewRating;
    private AAdapter aAdapter;
    private int test[];
    private Button ButtonGame, ButtonExit;
    private TextView tVTexrReating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);

        recyclerViewRating = findViewById(R.id.recyclerRatingMainMenu);
        recyclerViewRating.setHasFixedSize(true);
        //отвечает за форму отображения элемента
        recyclerViewRating.setLayoutManager(new LinearLayoutManager(this));

        FireBase.setRecyclerViewRating(recyclerViewRating);
        FireBase.showDate();

        //инициализация
        ButtonGame = findViewById(R.id.buttonGameMain_menu);
        ButtonExit = findViewById(R.id.main_menuButtonExit);

        tVTexrReating = findViewById(R.id.textRatingMain_menu);

        //

        ButtonGame.setText("Играть");
        ButtonExit.setText("Выход");
        //загрузка данных с БД
        FireBase.showStringAverageResult(Data.getEmailUser());
        tVTexrReating.setText(Data.stringAverageResult);


        MakeANumber makeANumber = new MakeANumber();
        makeANumber.NumberCreation();
        test = makeANumber.getNumber();

        ButtonGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Main_Menu.this, The_Game.class));
            }
        });
        ButtonExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FireBase.exitFB();
                startActivity(new Intent(Main_Menu.this, MainActivity.class));
            }
        });


    }

    @Override
    public void onStart() {
        super.onStart();
        tVTexrReating.setText(Data.stringAverageResult);
        Log.d(TAG, "onStart() called");
    }

    @Override
    public void onResume() {
        super.onResume();
        tVTexrReating.setText(Data.stringAverageResult);
        Log.d(TAG, "onResume() called");
    }

    @Override
    public void onPause() {
        super.onPause();
        tVTexrReating.setText(Data.stringAverageResult);
        Log.d(TAG, "onPause() called");
    }

    @Override
    public void onStop() {
        super.onStop();
        tVTexrReating.setText(Data.stringAverageResult);
        Log.d(TAG, "onStop() called");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        tVTexrReating.setText(Data.stringAverageResult);
        Log.d(TAG, "onDestroy() called");
    }


}
