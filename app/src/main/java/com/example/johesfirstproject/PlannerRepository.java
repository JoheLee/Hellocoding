package com.example.johesfirstproject;

import androidx.lifecycle.LiveData;

import java.util.List;

public class PlannerRepository {

    private PlannerDao plannerDao;
    private LiveData<List<Planner>> planner;

    // Note that in order to unit test the DiaryRepository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples
    PlannerRepository() {
        PlannerRoomDatabase db = PlannerRoomDatabase.getDatabase();
        plannerDao = db.PlannerDao();
        planner = plannerDao.getPlanner();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    LiveData<List<Planner>> getPlanners() {
        return planner;
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    void insert(Planner planner) {
        PlannerRoomDatabase.databaseWriteExecutor.execute(() -> {
            plannerDao.insert(planner);
        });
    }
}
