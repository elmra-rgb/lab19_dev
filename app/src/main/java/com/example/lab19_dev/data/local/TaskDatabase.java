package com.example.lab19_dev.data.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {TaskEntity.class}, version = 1, exportSchema = false)
public abstract class TaskDatabase extends RoomDatabase {

    public abstract TaskDao taskDao();

    private static volatile TaskDatabase databaseInstance;

    public static TaskDatabase getDatabaseInstance(Context context) {
        if (databaseInstance == null) {
            synchronized (TaskDatabase.class) {
                if (databaseInstance == null) {
                    databaseInstance = Room.databaseBuilder(
                                    context.getApplicationContext(),
                                    TaskDatabase.class,
                                    "tasks_database"
                            )
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return databaseInstance;
    }
}
