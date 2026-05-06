package com.example.lab19_dev.data.local;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tasks_table")
public class TaskEntity {

    @PrimaryKey(autoGenerate = true)
    private int itemId;

    private String itemTitle;
    private String itemDescription;

    public TaskEntity(String itemTitle, String itemDescription) {
        this.itemTitle = itemTitle;
        this.itemDescription = itemDescription;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public String getItemDescription() {
        return itemDescription;
    }
}
