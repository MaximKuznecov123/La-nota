package com.example.todo.Activities.MainActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todo.Activities.Task_Creator;
import com.example.todo.Adapter.TaskAdapter;
import com.example.todo.Model.TaskModel;
import com.example.todo.R;
import com.example.todo.UTILS.TasksHandler;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TasksActivity extends AppCompatActivity {
    private static TasksHandler db;

    private static TaskAdapter taskAdapter;

    private static List<TaskModel> taskList;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new TasksHandler(this);
        db.openDB();
        taskList = new ArrayList<>();

        RecyclerView taskRecyclerList = findViewById(R.id.taskRecyclerList);
        taskRecyclerList.setLayoutManager(new LinearLayoutManager(this));
        taskAdapter = new TaskAdapter(db, this);
        taskRecyclerList.setAdapter(taskAdapter);
        ExtendedFloatingActionButton fab = findViewById(R.id.faba);

        ItemTouchHelper itemTouchHelper = new
                ItemTouchHelper(new RecyclerItemTouchHelper(taskAdapter));
        itemTouchHelper.attachToRecyclerView(taskRecyclerList);

        fab.setOnClickListener(v -> {
            Intent i = new Intent(this, Task_Creator.class);
            startActivity(i);});
        DBloader();

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        DBloader();
    }
    public static void DBloader(){
        taskList = db.getAllTasks();
        Collections.reverse(taskList);
        taskAdapter.setTasks(taskList);
        taskAdapter.notifyDataSetChanged();
    }
}