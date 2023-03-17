package com.example.todo.Activities.MainActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todo.AbstractClasses.MyActivity;
import com.example.todo.Activities.Task_Creator;
import com.example.todo.Adapter.TaskAdapter;
import com.example.todo.Model.TaskModel;
import com.example.todo.R;
import com.example.todo.UTILS.TasksHandler;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class TasksActivity extends MyActivity {
    private TasksHandler db;

    private TaskAdapter taskAdapter;
    TextView text;

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

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        DBloader();
    }


    public void initWidgets(){
        taskRecyclerList = findViewById(R.id.taskRecyclerList);
        taskRecyclerList.setLayoutManager(new LinearLayoutManager(this));

        text = findViewById(R.id.text);
        String date = new SimpleDateFormat("dd-MM-yy").format(Calendar.getInstance().getTime());
        text.setText(date);

        fab = findViewById(R.id.faba);
        fab.setOnClickListener(v -> {
            Intent i = new Intent(this, Task_Creator.class);
            Task_Creator.adapter = taskAdapter;
            startActivity(i);});
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