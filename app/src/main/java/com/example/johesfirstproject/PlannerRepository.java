package com.example.johesfirstproject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.Transformations;

import java.util.List;

public class PlannerRepository {

    private PlannerDao plannerDao;
    private LiveData<List<Planner>> liveData;

    // Note that in order to unit test the DiaryRepository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples
    PlannerRepository() {
        PlannerRoomDatabase db = PlannerRoomDatabase.getDatabase();
        plannerDao = db.PlannerDao();

        liveData = plannerDao.getPlanner();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    LiveData<List<Planner>> getPlanners() {
        return liveData;
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    void insert(Planner planner) {
        PlannerRoomDatabase.databaseWriteExecutor.execute(() -> {
            plannerDao.insert(planner);
        });
    }

    void deleteItem(Planner plannerItem) {
        PlannerRoomDatabase.databaseWriteExecutor.execute(() -> {
            plannerDao.deleteItem(plannerItem);
        });
    }

    Planner getItem(String from, String to, String date) {
        List<Planner> list = liveData.getValue();
        for (int i = 0; i < list.size(); i++) {
            Planner item = list.get(i);
            if (item.from.equals(from) && item.to.equals(to) && item.date.equals(date)) {
                return item;
            }
        }
        return null;
    }
}
