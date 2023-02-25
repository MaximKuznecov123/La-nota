package com.example.todo.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.todo.Activities.MainActivity;
import com.example.todo.R;

public class Splash_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        final Intent i = new Intent(this, MainActivity.class);
        new Handler().postDelayed((Runnable) () ->  {
            startActivity(i);
            finish();
        }, 1000);
    }
}