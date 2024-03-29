package com.example.todo.Adapter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todo.Activities.TasksActivity;
import com.example.todo.Activities.Task_Creator;
import com.example.todo.Model.TaskModel;
import com.example.todo.R;
import com.example.todo.UTILS.TasksHandler;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {

    private List<TaskModel> todolist;
    private static TasksActivity activity;
    private final TasksHandler db;

    public TaskAdapter(TasksHandler db, TasksActivity activity){
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
        TaskModel item = todolist.get(position);
        holder.Name.setText(item.getTask());
        holder.Description.setText(item.getDescription());
        holder.task.setChecked(tobool(item.getStatus()));
        holder.task.setOnCheckedChangeListener((buttonView, isChecked) -> db.updateStatus(item.getId(),isChecked?1:0));

        holder.view.setOnClickListener((v) -> {
            editItem(position);
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
    public void setTasks(List<TaskModel> todolist){
        this.todolist = todolist;
        notifyDataSetChanged();
    }

    public void editItem(int position){
        TaskModel item = todolist.get(position);
            Bundle bundle = new Bundle();
            bundle.putInt("id",item.getId());
            bundle.putString("task", item.getTask());
            bundle.putString("descr", item.getDescription());

            Intent i = new Intent(activity, Task_Creator.class);
            i.putExtra("extraDATA", bundle);
            activity.startActivity(i);

    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        View view;
        CheckBox task;
        TextView Name, Description;
        ImageView bar;

        ViewHolder(View view) {
            super(view);
            this.view = view;

            task = view.findViewById(R.id.todocheckBox);
            Name = view.findViewById(R.id.name);
            Description = view.findViewById(R.id.description);
            bar = view.findViewById(R.id.color_bar);

            Drawable drawable = AppCompatResources.getDrawable(activity,R.drawable.event_list_colorbar);
            drawable.setTint(activity.getColor(R.color.purple_200));
            bar.setBackground(drawable);

        }
    }
}