package com.example.todo.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todo.Activities.MainActivity.MainActivity;
import com.example.todo.Activities.Task_Creator;
import com.example.todo.Model.ToDoModel;
import com.example.todo.R;
import com.example.todo.UTILS.DatabaseHandler;

import java.util.List;

public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.ViewHolder> {

    private List<ToDoModel> todolist;
    private MainActivity activity;
    private DatabaseHandler db;

    public ToDoAdapter(DatabaseHandler db, MainActivity activity){
        this.db = db;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.task_layout,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        db.openDB();
        ToDoModel item = todolist.get(position);
        holder.Name.setText(item.getTask());
        holder.Description.setText(item.getDescription());
        holder.task.setChecked(tobool(item.getStatus()));
        holder.task.setOnCheckedChangeListener((buttonView, isChecked) -> {
            db.updateStatus(item.getId(),isChecked?1:0);
        });

    }
    public boolean tobool(int  n){
        return n!=0;
    }

    @Override
    public int getItemCount() {
        return todolist.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setTasks(List<ToDoModel> todolist){
        this.todolist = todolist;
        notifyDataSetChanged();
    }

    public void deleteItem(int position){
        ToDoModel item = todolist.get(position);
        db.deleteTask(item.getId());
        todolist.remove(position);
        notifyItemRemoved(position);
    }

    public Context getContext(){
        return activity;
    }


    public void editItem(int position){
        ToDoModel item = todolist.get(position);
        try {
            Bundle bundle = new Bundle();
            bundle.putInt("id",item.getId());
            bundle.putString("task", item.getTask());
            bundle.putString("descr", item.getDescription());

            Intent i = new Intent(activity, Task_Creator.class);
            i.putExtra("extraDATA", bundle);
            activity.startActivity(i);

        }catch (Exception e){
            Log.e("(((((((", String.valueOf(e));
        }
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{
        CheckBox task;
        TextView Name, Description;

        ViewHolder(View view) {
            super(view);
            task = view.findViewById(R.id.todocheckBox);
            Name = view.findViewById(R.id.name);
            Description = view.findViewById(R.id.description);
        }
    }
}