package com.example.madventure;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegistrationActivity extends AppCompatActivity {
    EditText username, password, repassword, mail;
    Button signup;
    DBhelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        username = findViewById(R.id.mail);
        mail = findViewById(R.id.mail);
        password = findViewById(R.id.password);
        signup = findViewById(R.id.reg_signUp);
        repassword = findViewById(R.id.repassword);
        DB= new DBhelper(this);
        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String email = mail.getText().toString();
                String repass = repassword.getText().toString();
                if(TextUtils.isEmpty(user) || TextUtils.isEmpty(pass) || TextUtils.isEmpty(email) || TextUtils.isEmpty(repass))
                    alert.setMessage("Заполните все поля").show();
                else{
                    if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() && !TextUtils.isEmpty(email))
                        alert.setMessage("Неверная валидация поля Mail").show();
                    else
                    if(pass.equals(repass)){
                        Boolean checkuser = DB.checkusername(user);
                        if(checkuser==false){
                            Boolean insert = DB.insertData(user,pass,email);
                            if(insert==true){
                                alert.setMessage("Регистрация прошла успешно").show();
                                Intent i = new Intent(getApplicationContext(),LoginActivity.class);
                                startActivity(i);
                            } else{
                                alert.setMessage("Ошибка регистрации").show();
                            }
                        }else{
                            alert.setMessage("Пользователь уже существует").show();
                        }
                    }else{
                        alert.setMessage("Пароли не совпадают").show();
                    }

                }
            }
        });
    }
}