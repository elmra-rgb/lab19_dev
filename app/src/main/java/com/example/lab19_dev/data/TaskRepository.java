package com.example.lab19_dev.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.lab19_dev.data.local.TaskDao;
import com.example.lab19_dev.data.local.TaskDatabase;
import com.example.lab19_dev.data.local.TaskEntity;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TaskRepository {

    private final TaskDao taskDao;
    private final LiveData<List<TaskEntity>> allTasks;
    private final ExecutorService backgroundExecutor;

    public TaskRepository(Application application) {
        TaskDatabase database = TaskDatabase.getDatabaseInstance(application);
        taskDao = database.taskDao();
        allTasks = taskDao.fetchAllTasks();
        backgroundExecutor = Executors.newSingleThreadExecutor();
    }

    public void addTask(TaskEntity task) {
        backgroundExecutor.execute(() -> taskDao.insertTask(task));
    }

    public void removeTask(TaskEntity task) {
        backgroundExecutor.execute(() -> taskDao.deleteTask(task));
    }

    public void clearAllTasks() {
        backgroundExecutor.execute(taskDao::eraseAllTasks);
    }

    public LiveData<List<TaskEntity>> getAllTasks() {
        return allTasks;
    }
}
