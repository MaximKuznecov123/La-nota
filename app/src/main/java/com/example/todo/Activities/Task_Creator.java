package com.example.todo.Activities;





import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.todo.Model.ToDoModel;
import com.example.todo.R;
import com.example.todo.UTILS.DatabaseHandler;

public class Task_Creator extends AppCompatActivity {

    private EditText task, descr;
    private Button createTask;
    private DatabaseHandler db;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_creator);

        task = findViewById(R.id.Name);
        descr = findViewById(R.id.description);
        createTask = findViewById(R.id.taskCreate);

        createTask.setOnClickListener(this::onCreateTask);
    }


    private void onCreateTask(View view) {
        db = new DatabaseHandler(this);
        db.openDB();
        ToDoModel newtask = new ToDoModel();
        newtask.setTask(String.valueOf(task.getText()));
        newtask.setDescription(String.valueOf(descr.getText()));
        newtask.setStatus(0);
        db.insertTask(newtask);
        finish();
    }


}