package com.example.lab19_dev.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.lab19_dev.data.TaskRepository;
import com.example.lab19_dev.data.local.TaskEntity;

import java.util.List;

public class TaskViewModel extends AndroidViewModel {

    private final TaskRepository taskRepository;
    private final LiveData<List<TaskEntity>> allTasks;

    public TaskViewModel(@NonNull Application application) {
        super(application);
        taskRepository = new TaskRepository(application);
        allTasks = taskRepository.getAllTasks();
    }

    public void addNewTask(TaskEntity task) {
        taskRepository.addTask(task);
    }

    public void deleteExistingTask(TaskEntity task) {
        taskRepository.removeTask(task);
    }

    public void deleteAllExistingTasks() {
        taskRepository.clearAllTasks();
    }

    public LiveData<List<TaskEntity>> observeAllTasks() {
        return allTasks;
    }
}
