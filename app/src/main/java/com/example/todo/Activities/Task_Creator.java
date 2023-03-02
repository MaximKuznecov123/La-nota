package com.example.todo.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.todo.Model.TaskModel;
import com.example.todo.R;
import com.example.todo.UTILS.TasksHandler;

public class Task_Creator extends AppCompatActivity {

    private EditText task, description;
    private TasksHandler db;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createtask);

        task = findViewById(R.id.Name);
        description = findViewById(R.id.description);
        Button createTask = findViewById(R.id.taskCreate);

        Intent i = getIntent();
        Bundle bundle = i.getBundleExtra("extraDATA");

        db = new TasksHandler(this);
        db.openDB();

        if(bundle != null){
            int id = bundle.getInt("id");
            String tasktext = bundle.getString("task");
            String descr = bundle.getString("descr");

            task.setText(tasktext);
            description.setText(descr);
            createTask.setOnClickListener(view -> onUpdateTask(view, id));
        }else {
            createTask.setOnClickListener(this::onCreateTask);
        }
    }

    private void onCreateTask(View view) {
        String s = String.valueOf(task.getText());
        if(s.equals("")){
            Toast.makeText(this, "Заголовок не может быть пустым", Toast.LENGTH_SHORT).show();
        }else{
            TaskModel newtask = new TaskModel();
            newtask.setTask(s);
            newtask.setDescription(String.valueOf(description.getText()));
            newtask.setStatus(0);
            db.insertTask(newtask);
            finish();
        }
    }

    private void onUpdateTask(View view, int id){
        String s = String.valueOf(task.getText());
        if(s.equals("")){
            Toast.makeText(this, "Заголовок не может быть пустым", Toast.LENGTH_SHORT).show();
        }else {
            db.updateTask(id, s);
            db.updateDescr(id, String.valueOf(description.getText()));
            finish();
        }
    }
}