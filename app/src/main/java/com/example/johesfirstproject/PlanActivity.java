package com.example.johesfirstproject;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.johesfirstproject.databinding.PlanactivityBinding;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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

    }
}