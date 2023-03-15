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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class TasksActivity extends AppCompatActivity {
    private TasksHandler db;

    private TaskAdapter taskAdapter;

    private List<TaskModel> taskList;
    private  RecyclerView taskRecyclerList;
    private  ExtendedFloatingActionButton fab;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("");

        initWidgets();
        initDB();
        DBloader();

            String currentDate = new SimpleDateFormat("dd-MM-yy", Locale.getDefault()).format(new Date());

        SetRecyclerItemTouchView();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        DBloader();
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

    public void initDB(){
        db = new TasksHandler(this);
        db.openDB();
        taskList = new ArrayList<>();
        taskAdapter = new TaskAdapter(db, this);
        taskRecyclerList.setAdapter(taskAdapter);
    }

    public void DBloader(){
        taskList = db.getAllTasks();
        Collections.reverse(taskList);
        taskAdapter.setTasks(taskList);
        taskAdapter.notifyDataSetChanged();
    }
}