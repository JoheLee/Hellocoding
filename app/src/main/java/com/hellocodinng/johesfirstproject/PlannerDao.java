package com.hellocodinng.johesfirstproject;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PlannerDao {
    // allowing the insert of the same word multiple times by passing a
    // conflict resolution strategy
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Planner planner);

    @Delete()
    void deleteItem(Planner planner);

    @Query("SELECT * FROM planner_table ORDER BY subject ASC")
    LiveData<List<Planner>> getPlanner();
}
