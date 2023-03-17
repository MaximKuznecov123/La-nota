package com.example.todo.AbstractClasses;

import android.content.pm.ActivityInfo;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class MyActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("");
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
    }
}

