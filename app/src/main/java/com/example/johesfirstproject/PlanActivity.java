package com.example.johesfirstproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.johesfirstproject.databinding.PlanactivityBinding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class PlanActivity extends AppCompatActivity {

    PlanactivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        binding = PlanactivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Date currentTime = Calendar.getInstance().getTime();

        String currentDate = new SimpleDateFormat("MM-dd-yyyy", Locale.getDefault()).format(currentTime);
        binding.date.setText(currentDate);

        // TODO: Start here
        ArrayList<String> source = AddItemsToRecyclerViewArrayList();
        binding.date.setText(source.get(0));
        binding.date.setText(source.get(1));
        binding.date.setText(source.get(2));
        binding.date.setText(source.get(3));
        binding.date.setText(source.get(4));
        binding.date.setText(source.get(5));
        binding.date.setText(source.get(6));

    }

    public ArrayList<String> AddItemsToRecyclerViewArrayList()
    {
        // Adding items to ArrayList
        ArrayList<String> source = new ArrayList<>();

        source.add(getFormattedDate(0));
        source.add(getFormattedDate(1));
        source.add(getFormattedDate(2));
        source.add(getFormattedDate(3));
        source.add(getFormattedDate(4));
        source.add(getFormattedDate(5));
        source.add(getFormattedDate(6));
        return source;
    }

    private String getFormattedDate(int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, day);
        return new SimpleDateFormat("dd/EEE", Locale.getDefault()).format(calendar.getTime());
    }
}