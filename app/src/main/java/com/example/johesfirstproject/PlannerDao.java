package com.example.johesfirstproject;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PlannerDao {
    // allowing the insert of the same word multiple times by passing a
    // conflict resolution strategy
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Planner planner);

    @Query("DELETE FROM planner_table")
    void deleteAll();

    @Query("SELECT * FROM planner_table ORDER BY subject ASC")
    LiveData<List<Planner>> getPlanner();
}
