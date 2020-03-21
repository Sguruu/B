package com.example.bullsandows;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;


public class MainActivity extends AppCompatActivity {

    private EditText email, password;
    private String emailString, passwordString;
    private Button autorizBut, registrBut;
    protected boolean logic = false;
    // List<Model> modelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FireBase.getInstance();


        //временно убрать
        // FireBase.exitFB();
        //FireBase.showDate();
        // FireBase.uploadDataBase();

        //System.out.println("Проверка "+ Data.modelList.get(1).getId());
        //System.out.println("Проверка "+ Data.modelList.get(2).getId());


        // System.out.println("Загрузка данных с БД "+modelList.get);

        logic = FireBase.setAuthInit(); //проверям окончена ли прошлая сессия или нет
        if (logic) {
            Data.setEmailAut(FireBase.mAuth.getCurrentUser().getEmail());
            FireBase.showDate();
            startActivity(new Intent(MainActivity.this, Main_Menu.class));
        }

        //если пользователь закончил сессию начинаем инициализировать нашу активити и ее составляющие

        email = findViewById(R.id.emailActivityMain);
        password = findViewById(R.id.passwordActivityMain);
        autorizBut = findViewById(R.id.buttonAutoriActivityMain);
        registrBut = findViewById(R.id.buttonRegistActivityMain);
        autorizBut.setText("Войти");
        registrBut.setText("Зарегестрироваться");
        email.setHint("email");
        password.setHint("password");


        autorizBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Butterfly.validateForm(email, password)) {

                    FireBase.mAuth.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString()).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Data.setEmailAut(FireBase.mAuth.getCurrentUser().getEmail());
                                FireBase.showDate();
                                Toast.makeText(MainActivity.this, "Авторизация успешна", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(MainActivity.this, Main_Menu.class));
                            } else {
                                Toast.makeText(MainActivity.this, "Авторизация не прошла", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }); //передаем наш емайл и парроль + добавим слушателя выполненого входа
                } else
                    Toast.makeText(MainActivity.this, "Вы не заполнили поля", Toast.LENGTH_SHORT).show();
            }
        });

        registrBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Regestration.class));

            }
        });


    }


}
