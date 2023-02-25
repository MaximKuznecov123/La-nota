package com.example.todo.Activities;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todo.Adapter.ToDoAdapter;
import com.example.todo.Model.ToDoModel;
import com.example.todo.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView taskRecyclerList;
    private ToDoAdapter taskAdapter;

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

        taskAdapter.setTasks(taskList);

    }

}