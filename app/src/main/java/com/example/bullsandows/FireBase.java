package com.example.bullsandows;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bullsandows.adapter.AAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class FireBase extends AppCompatActivity {
    static FirebaseAuth mAuth; //аунтификациия и оешисьоацтя
    private static boolean Buf;
    private static FireBase instance;
    private static AAdapter aAdapter;
    private static RecyclerView recyclerViewRating;


    public static RecyclerView getRecyclerViewRating() {
        return recyclerViewRating;
    }

    public static void setRecyclerViewRating(RecyclerView recyclerViewRating) {
        FireBase.recyclerViewRating = recyclerViewRating;
    }

    private static FirebaseFirestore db = FirebaseFirestore.getInstance();

    //конструктор типа класса Sington
    public static FireBase getInstance() {
        if (instance == null)
            instance = new FireBase();
        return instance;
    }

    public FireBase() {
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }


    public static boolean setAuthInit() {
// Initialize Firebase Auth //зашел ли пользователь или нет ?
        mAuth = FirebaseAuth.getInstance();

        // Check if user is signed in (non-null) and update UI accordingly. Проверяет вошел ли пользователь
        //открыта ли сессия или нет ?
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser != null) {
            return true;
        } else
            return false;
    }


    //метод регистрации
    public void registration(EditText em, final EditText pd1, final EditText pd2, final Activity activity, final Context context) { //метод регистрации
        final String email, password1, password2;
        email = em.getText().toString();
        password1 = pd1.getText().toString();
        mAuth.createUserWithEmailAndPassword(email, password1).addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {

            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {
                    Toast.makeText(activity, "Регистрация успешна", Toast.LENGTH_SHORT).show();
                    Data.setEmailUser(email); //сохраняем емайл пользователя
                    FireBase.uploadDataBaseReg();
                    Intent intent = new Intent(context, MainActivity.class);
                    startActivity(intent);
                } else
                    Toast.makeText(activity, "Регистрация не прошла", Toast.LENGTH_SHORT).show();
            }
        });


    }

    //завершает сеанс пользователя
    public static void exitFB() {
        FirebaseAuth.getInstance().signOut();
    }


    //функции отвечающие за работу с ДБ FireBase

    //Функция добавление информации в БД

    public static void uploadDataBase(double averageResult, String stringAverageResult) {
        // String id = UUID.randomUUID().toString();
        System.out.println("почта потча " + Data.getEmailUser());
        Map<String, Object> user = new HashMap<>();
        user.put("id", Data.getEmailUser()); //id for date
        user.put("userID", "тест");
        user.put("averageResult", averageResult);
        user.put("stringAverageResult", stringAverageResult); //тут должны быть только цифры !
        db.collection("BullsAndCows").document(Data.getEmailUser())
                .set(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        //Log.d(, "DocumentSnapshot successfully written!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Log.w(TAG, "Error writing document", e);
                    }
                });


    }

    //Функция добавление информации в БД

    public static void uploadDataBaseReg() {
        // String id = UUID.randomUUID().toString();
        System.out.println("почта потча " + Data.getEmailUser());
        Map<String, Object> user = new HashMap<>();
        user.put("id", Data.getEmailUser()); //id for date
        user.put("userID", "тест");
        user.put("averageResult", 0);
        user.put("stringAverageResult", "0"); //тут должны быть только цифры !
        db.collection("BullsAndCows").document(Data.getEmailUser())
                .set(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        //Log.d(, "DocumentSnapshot successfully written!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Log.w(TAG, "Error writing document", e);
                    }
                });


    }

    //функция получения данных с БД
    public static void showDate() {
        db.collection("BullsAndCows")
                .orderBy("averageResult", Query.Direction.ASCENDING)
                .limit(3)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        Data.modelList.clear();
                        System.out.println("Функция получения 2===============================" +
                                "====================================" +
                                "=========================" +
                                "============================" +
                                "============== ");
                        for (QueryDocumentSnapshot user : task.getResult()) {

                            Model model = new Model(user.getString("id")
                                    , user.getString("userID")
                                    , user.getString("stringAverageResult"));
                            Data.modelList.add(model);

                            System.out.println("Функция получения 2===============================" +
                                    "====================================" +
                                    "=========================" +
                                    "============================" +
                                    "============== " + user.getString("id"));
                            user.getString("id");
                            System.out.println("Проверка модели ================================ " + Data.modelList.get(0).getId());
                            System.out.println("Проверка модели ================================ " + Data.modelList.get(0).getStringAverageResult());


                        }
                        FireBase.aAdapter = new AAdapter(Data.modelList);
                        FireBase.recyclerViewRating.setAdapter(FireBase.aAdapter);
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });


    }

    //функция получения stringAverageResult  с FB
    public static String showStringAverageResult(String email) {
        DocumentReference docRef = db.collection("BullsAndCows").document(email);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    Data.stringAverageResult = document.getString("stringAverageResult");
                    System.out.println("Проверка модели ================================ ++++++++++++++++++++++++++++++++ " + document.getString("stringAverageResult"));
                }
            }
        });

        return Data.stringAverageResult;
    }


}
