package com.example.todo.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todo.Adapter.ToDoAdapter;
import com.example.todo.Model.ToDoModel;
import com.example.todo.R;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView taskRecyclerList;
    private ToDoAdapter taskAdapter;
    private ExtendedFloatingActionButton fab;

    private List<ToDoModel> taskList;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        taskList = new ArrayList<>();

        taskRecyclerList = findViewById(R.id.taskRecyclerList);
        taskRecyclerList.setLayoutManager(new LinearLayoutManager(this));
        taskAdapter = new ToDoAdapter(this);
        taskRecyclerList.setAdapter(taskAdapter);
        ToDoModel task = new ToDoModel();
        task.setTask("TEST");
        task.setDescription("description");
        task.setStatus(0);
        task.setId(1);

        taskList.add(task);taskList.add(task);
        taskList.add(task);taskList.add(task);
        taskList.add(task);taskList.add(task);
        taskList.add(task);
        taskList.add(task);
        taskList.add(task);
        taskList.add(task);

        try {
        fab = findViewById(R.id.faba);


    fab.setOnClickListener(v -> {
        Intent i = new Intent(this, Task_Creator.class);
        startActivity(i);
        finish();
    });
}catch (Exception e){
            e.printStackTrace();
            Log.e("((((((((((((((((((", String.valueOf(e));
}


        taskAdapter.setTasks(taskList);

    }

}