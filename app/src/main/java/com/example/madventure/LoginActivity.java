package com.example.madventure;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {
    TextView next;
    EditText username, password;
    Button signin, signup;
    DBhelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        next = findViewById(R.id.textView17);
        username = findViewById(R.id.username1);
        password = findViewById(R.id.password1);
        signin = findViewById(R.id.SignIn1);
        DB = new DBhelper(this);
        AlertDialog.Builder alert = new AlertDialog.Builder(this);


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(i);
                finish();
            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = username.getText().toString();
                String pass = password.getText().toString();

                if(TextUtils.isEmpty(user) || TextUtils.isEmpty(pass))
                    alert.setMessage("Заполните все поля").show();
                else{
                    Boolean checkuserpass = DB.checkusernamepassword(user,pass);
                    if(checkuserpass==true){
                        alert.setMessage("Авторизация прошла успешно").show();
                        Intent i = new Intent(getApplicationContext(), MenuActivity.class);
                        startActivity(i);
                        finish();
                    }else{
                        alert.setMessage("Ошибка авторизации").show();
                    }
                }
            }
        });
    }
}