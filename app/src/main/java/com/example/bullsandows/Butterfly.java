package com.example.bullsandows;

import android.text.TextUtils;
import android.widget.EditText;

public class Butterfly {

    //метод ловит пустые значения
    public static boolean validateForm(EditText el, EditText pd) {
        boolean valid = true;
        String email = el.getText().toString();
        if (TextUtils.isEmpty(email)) {
            el.setError("Необходимо заполнить поле");
            valid = false;
        } else {
            el.setError(null);
        }
        String password = pd.getText().toString();
        if (TextUtils.isEmpty(password)) {
            pd.setError("Необходимо заполнить поле");
            valid = false;
        } else {
            pd.setError(null);
        }
        return valid;
    }

    public static boolean validateForm2(EditText el, EditText pd1, EditText pd2) {
        boolean valid = true;
        String email = el.getText().toString();
        if (TextUtils.isEmpty(email)) {
            el.setError("Необходимо заполнить поле");
            valid = false;
        } else {
            el.setError(null);
        }
        String password = pd1.getText().toString();
        if (TextUtils.isEmpty(password)) {
            pd1.setError("Необходимо заполнить поле");
            valid = false;
        } else {
            pd1.setError(null);
        }
        String password1 = pd2.getText().toString();
        if (TextUtils.isEmpty(password1)) {
            pd2.setError("Необходимо заполнить поле");
            valid = false;
        } else {
            pd2.setError(null);
        }
        return valid;
    }

}
