package com.example.johesfirstproject;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.example.johesfirstproject.databinding.PlanactivityBinding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

        // TODO: Start here
        ArrayList<String> source = getNext7Days();
        binding.date1.setText(source.get(0));
        binding.date2.setText(source.get(1));
        binding.date3.setText(source.get(2));
        binding.date4.setText(source.get(3));
        binding.date5.setText(source.get(4));
        binding.date6.setText(source.get(5));
        binding.date7.setText(source.get(6));

        binding.twelveam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show Dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(PlanActivity.this);
                builder.setMessage("From 01AM to 02AM");
                // Get the layout inflater
                LayoutInflater inflater = getLayoutInflater();
                // Inflate and set the layout for the dialog
                // Pass null as the parent view because its going in the dialog layout
                View view = inflater.inflate(R.layout.dialog, null);
                // TODO:
                //  NumberPicker picker = view.findViewById(R.id.picker);


                builder.setView(view)
                        // Add action buttons
                        .setPositiveButton("Update", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                // sign in the user ...
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                            }
                        });
                builder.create().show();
            }
        });
    }

    public ArrayList<String> getNext7Days()
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