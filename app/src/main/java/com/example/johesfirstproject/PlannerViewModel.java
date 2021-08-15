package com.example.johesfirstproject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class PlannerViewModel extends ViewModel {

    private PlannerRepository repository = new PlannerRepository();

    private final LiveData<List<Planner>> planner = repository.getPlanners();

    LiveData<List<Planner>> getPlanner() { return planner; }

    public void insert(Planner planner) { repository.insert(planner); }
}