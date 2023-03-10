package com.example.todo.UTILS;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.todo.Model.TaskModel;

import java.util.ArrayList;
import java.util.List;

public class TasksHandler extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String NAME = "toDoListDatabase";
    private static final String TODO_TABLE = "todo";
    private static final String ID = "id";
    private static final String TASK = "task";
    private static final String DESCR = "descr";
    private static final String STATUS = "status";
    private static final String CREATE_TODO_TABLE = "CREATE TABLE " + TODO_TABLE +
            "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + TASK + " TEXT, "
            + DESCR + " TEXT, "
            + STATUS + " NUMERIC)";
    private SQLiteDatabase db;

    public TasksHandler(Context context){
        super(context, NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TODO_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
        //drop the older tables
        db.execSQL("DROP TABLE IF EXISTS " + TODO_TABLE);
        //create table again
        onCreate(db);
    }

    public void openDB(){
        db = this.getWritableDatabase();
    }

    public void insertTask(TaskModel task){
        ContentValues cv = new ContentValues();
        cv.put(TASK, task.getTask());
        cv.put(DESCR, task.getDescription());
        cv.put(STATUS, 0);
        db.insert(TODO_TABLE,null,cv);
    }

    @SuppressLint("Range")
    public List<TaskModel> getAllTasks(){
        List<TaskModel> taskList = new ArrayList<>();
        Cursor cur = null;
        db.beginTransaction();
        try{
            cur = db.query(TODO_TABLE, null,null,null,null,null,null,null);
            if(cur != null){
                if (cur.moveToFirst()){
                    do {
                        TaskModel task = new TaskModel();
                        task.setId(cur.getInt(cur.getColumnIndex(ID)));
                        task.setTask(cur.getString(cur.getColumnIndex(TASK)));
                        task.setDescription(cur.getString(cur.getColumnIndex(DESCR)));
                        task.setStatus(cur.getInt(cur.getColumnIndex(STATUS)));
                        taskList.add(task);
                    }while(cur.moveToNext());
                }
            }
        }
        finally {
            db.endTransaction();
            assert cur != null;
            cur.close();
        }
        return taskList;
    }

    public void updateStatus(int id, int status){
        ContentValues cv = new ContentValues();
        cv.put(STATUS, status);
        db.update(TODO_TABLE, cv, ID + "= ?", new String[]{String.valueOf(id)} );
    }
    public void updateTask(int id, String task){
        ContentValues cv = new ContentValues();
        cv.put(TASK, task);
        db.update(TODO_TABLE, cv, ID + "= ?", new String[]{String.valueOf(id)} );
    }

    public void updateDescr(int id, String descr){
        ContentValues cv = new ContentValues();
        cv.put(DESCR, descr);
        db.update(TODO_TABLE, cv, ID + "= ?", new String[]{String.valueOf(id)} );
    }
    public void deleteTask(int id){
        db.delete(TODO_TABLE, ID + "= ?", new String[] {String.valueOf(id)});
    }
}

