package com.example.madventure;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class tutorial3Activity extends AppCompatActivity {
    TextView next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial3);

        next = findViewById(R.id.textView10);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(tutorial3Activity.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}