package com.example.lab19_dev.data.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TaskDao {

    @Insert
    void insertTask(TaskEntity task);

    @Delete
    void deleteTask(TaskEntity task);

    @Query("DELETE FROM tasks_table")
    void eraseAllTasks();

    @Query("SELECT * FROM tasks_table ORDER BY itemId DESC")
    LiveData<List<TaskEntity>> fetchAllTasks();
}
