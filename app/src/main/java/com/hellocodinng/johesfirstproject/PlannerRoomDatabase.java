package com.hellocodinng.johesfirstproject;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Planner.class}, version = 1, exportSchema = false)
public abstract class PlannerRoomDatabase extends RoomDatabase {

    public abstract PlannerDao PlannerDao();

    private static volatile PlannerRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static PlannerRoomDatabase getDatabase() {
        if (INSTANCE == null) {
            synchronized (PlannerRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(MyApplication.getInstance(),
                            PlannerRoomDatabase.class, "planner_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}