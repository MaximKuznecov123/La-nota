package com.example.todo.Activities;





import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.todo.R;

public class Task_Creator extends AppCompatActivity {

    EditText task;
    Button createTask;
    SharedPreferences taskName;
    {taskName = getPreferences(MODE_PRIVATE);}

    private final String NameSaved = " ";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_creator);

        task = findViewById(R.id.Name);
        createTask = findViewById(R.id.taskCreate);

        createTask.setOnClickListener(this::onCreateTask);

        loadText();
    }

    private void onCreateTask(View view){
        SharedPreferences.Editor ed = taskName.edit();
        ed.putString(NameSaved, task.getText().toString());
        ed.apply();
    }

    private void loadText(){
        String savedText = taskName.getString(NameSaved, "");
        task.setText(savedText);
    }

    @Override
    protected void onPause() {
        super.onPause();
        loadText();
    }
}