package com.example.bullsandows;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Regestration extends AppCompatActivity {
    private EditText email, password1, password2;
    private Button registrBut;
    protected boolean logic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.regestration);
        FireBase.getInstance();


        email = findViewById(R.id.loginRegestration);
        password1 = findViewById(R.id.password1Regestration);
        password2 = findViewById(R.id.password2Regestration);
        registrBut = findViewById(R.id.buttonRegestration);
        email.setHint("Введите email");
        password1.setHint("Придумайте пароль, не менее 6 знаков");
        password2.setHint("Повторите пароль");
        registrBut.setText("ЗАРЕГЕСТРИРОВАТЬСЯ");

        registrBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Butterfly.validateForm2(email, password1, password2)) {
                    if (password1.getText().toString().equals(password2.getText().toString())) {
                        FireBase fireBase;
                        fireBase = FireBase.getInstance();


                        fireBase.registration(email, password1, password2, Regestration.this, Regestration.this);
                        //останавливает поток на 5 сек
                        try {
                            Thread.sleep(5000);

                        } catch (Exception e) {
                        }
                    } else
                        Toast.makeText(Regestration.this, "Пароли не совпадают", Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(Regestration.this, "Вы не заполнили поля", Toast.LENGTH_SHORT).show();


            }


        });


    }
}
