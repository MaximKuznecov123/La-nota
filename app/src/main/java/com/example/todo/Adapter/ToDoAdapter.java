package com.example.todo.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todo.Activities.MainActivity;
import com.example.todo.Model.ToDoModel;
import com.example.todo.R;

import java.util.List;

public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.ViewHolder> {

    private List<ToDoModel> todolist;
    public MainActivity activity;

    public ToDoAdapter(MainActivity activity){
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.task_layout,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ToDoModel item = todolist.get(position);
        holder.Name.setText(item.getTask());
        holder.Description.setText(item.getDescription());
        holder.task.setChecked(tobool(item.getStatus()));

    }
    public boolean tobool(int  n){
        return n!=0;
    }

    @Override
    public int getItemCount() {
        return todolist.size();
    }

    public void setTasks(List<ToDoModel> todolist){
        this.todolist = todolist;
        notifyDataSetChanged();
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