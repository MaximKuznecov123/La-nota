package com.example.todo.Activities.MainActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

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
    private static RecyclerView taskRecyclerList;
    private static ExtendedFloatingActionButton fab;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("");

        initWidgets();
        SetRecyclerItemTouchView();

        initDB();
        DBloader();
        
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        DBloader();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    public void initDB(){
        db = new TasksHandler(this);
        db.openDB();
        taskList = new ArrayList<>();
        taskAdapter = new TaskAdapter(db, this);
        taskRecyclerList.setAdapter(taskAdapter);
    }

    public void initWidgets(){
        taskRecyclerList = findViewById(R.id.taskRecyclerList);
        taskRecyclerList.setLayoutManager(new LinearLayoutManager(this));

        fab = findViewById(R.id.faba);
        fab.setOnClickListener(v -> {
            Intent i = new Intent(this, Task_Creator.class);
            startActivity(i);});
    }

    public void SetRecyclerItemTouchView(){
        ItemTouchHelper itemTouchHelper = new
                ItemTouchHelper(new RecyclerItemTouchHelper(taskAdapter));
        itemTouchHelper.attachToRecyclerView(taskRecyclerList);
    }

    public void DBloader(){
        taskList = db.getAllTasks();
        Collections.reverse(taskList);
        taskAdapter.setTasks(taskList);
        taskAdapter.notifyDataSetChanged();
    }
}