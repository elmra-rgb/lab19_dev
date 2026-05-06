package com.example.lab19_dev.ui;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab19_dev.R;
import com.example.lab19_dev.data.local.TaskEntity;
import com.example.lab19_dev.viewmodel.TaskViewModel;

public class MainActivity extends AppCompatActivity {

    private TaskViewModel taskViewModel;
    private EditText titleInput;
    private EditText descriptionInput;
    private Button addTaskButton;
    private Button deleteAllButton;
    private TaskAdapter taskAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeViews();
        setupRecyclerView();
        setupViewModel();
        observeTaskChanges();
        setupClickListeners();
    }

    private void initializeViews() {
        titleInput = findViewById(R.id.inputTitle);
        descriptionInput = findViewById(R.id.inputDescription);
        addTaskButton = findViewById(R.id.addButton);
        deleteAllButton = findViewById(R.id.eraseAllButton);
    }

    private void setupRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.taskRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        
        taskAdapter = new TaskAdapter();
        recyclerView.setAdapter(taskAdapter);
    }

    private void setupViewModel() {
        taskViewModel = new ViewModelProvider(this).get(TaskViewModel.class);
    }

    private void observeTaskChanges() {
        taskViewModel.observeAllTasks().observe(this, tasks -> {
            taskAdapter.updateTaskList(tasks);
        });
    }

    private void setupClickListeners() {
        addTaskButton.setOnClickListener(v -> addNewTask());
        
        deleteAllButton.setOnClickListener(v -> {
            taskViewModel.deleteAllExistingTasks();
            Toast.makeText(this, "All tasks removed", Toast.LENGTH_SHORT).show();
        });

        taskAdapter.setLongClickCallback(task -> {
            taskViewModel.deleteExistingTask(task);
            Toast.makeText(this, "Task deleted: " + task.getItemTitle(), Toast.LENGTH_SHORT).show();
        });

        taskAdapter.setClickCallback(task -> {
            Toast.makeText(this, "Selected: " + task.getItemTitle(), Toast.LENGTH_SHORT).show();
        });
    }

    private void addNewTask() {
        String taskTitle = titleInput.getText().toString().trim();
        String taskDescription = descriptionInput.getText().toString().trim();

        if (taskTitle.isEmpty() || taskDescription.isEmpty()) {
            Toast.makeText(this, "Please fill both title and description", Toast.LENGTH_SHORT).show();
            return;
        }

        TaskEntity newTask = new TaskEntity(taskTitle, taskDescription);
        taskViewModel.addNewTask(newTask);

        titleInput.setText("");
        descriptionInput.setText("");
        
        Toast.makeText(this, "Task added successfully", Toast.LENGTH_SHORT).show();
    }
}
