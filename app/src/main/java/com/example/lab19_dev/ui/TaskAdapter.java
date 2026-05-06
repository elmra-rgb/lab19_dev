package com.example.lab19_dev.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab19_dev.R;
import com.example.lab19_dev.data.local.TaskEntity;

import java.util.ArrayList;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    private List<TaskEntity> taskList = new ArrayList<>();
    private ItemClickCallback clickCallback;
    private ItemLongClickCallback longClickCallback;

    public interface ItemClickCallback {
        void onItemClicked(TaskEntity task);
    }

    public interface ItemLongClickCallback {
        void onItemLongClicked(TaskEntity task);
    }

    public void updateTaskList(List<TaskEntity> tasks) {
        this.taskList = tasks;
        notifyDataSetChanged();
    }

    public void setClickCallback(ItemClickCallback callback) {
        this.clickCallback = callback;
    }

    public void setLongClickCallback(ItemLongClickCallback callback) {
        this.longClickCallback = callback;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.task_item, parent, false);
        return new TaskViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        TaskEntity currentTask = taskList.get(position);
        holder.titleText.setText(currentTask.getItemTitle());
        holder.descriptionText.setText(currentTask.getItemDescription());
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    class TaskViewHolder extends RecyclerView.ViewHolder {
        private final TextView titleText;
        private final TextView descriptionText;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);

            titleText = itemView.findViewById(R.id.displayTitle);
            descriptionText = itemView.findViewById(R.id.displayDescription);

            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (clickCallback != null && position != RecyclerView.NO_POSITION) {
                    clickCallback.onItemClicked(taskList.get(position));
                }
            });

            itemView.setOnLongClickListener(v -> {
                int position = getAdapterPosition();
                if (longClickCallback != null && position != RecyclerView.NO_POSITION) {
                    longClickCallback.onItemLongClicked(taskList.get(position));
                    return true;
                }
                return false;
            });
        }
    }
}
