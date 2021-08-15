package com.example.johesfirstproject;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.johesfirstproject.databinding.PlanactivityBinding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class PlanActivity extends AppCompatActivity {

    PlanactivityBinding binding;
    PlannerViewModel viewModel;
    String currentlySelectedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = PlanactivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(PlannerViewModel.class);

        Date currentTime = Calendar.getInstance().getTime();

        String currentDate = new SimpleDateFormat("MM-dd-yyyy", Locale.ENGLISH).format(currentTime);
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

        currentlySelectedDate = source.get(0);

        binding.twelveam.setOnClickListener(onClickListener("12 AM", "01 AM"));
        binding.oneam.setOnClickListener(onClickListener("01 AM", "02 AM"));
        binding.twoam.setOnClickListener(onClickListener("02 AM", "03 AM"));
        binding.threeam.setOnClickListener(onClickListener("03 AM", "04 AM"));
        binding.fouram.setOnClickListener(onClickListener("04 AM", "05 AM"));
        binding.fiveam.setOnClickListener(onClickListener("05 AM", "06 AM"));
        binding.sixam.setOnClickListener(onClickListener("06 AM", "07 AM"));
        binding.sevenam.setOnClickListener(onClickListener("07 AM", "08 AM"));
        binding.eightam.setOnClickListener(onClickListener("08 AM", "09 AM"));
        binding.nineam.setOnClickListener(onClickListener("09 AM", "10 AM"));
        binding.tenam.setOnClickListener(onClickListener("10 AM", "11 AM"));
        binding.elevenam.setOnClickListener(onClickListener("11 AM", "12 PM"));
        binding.twelvepm.setOnClickListener(onClickListener("12 PM", "01 PM"));
        binding.onepm.setOnClickListener(onClickListener("01 PM", "02 PM"));
        binding.twopm.setOnClickListener(onClickListener("02 PM", "03 PM"));
        binding.threepm.setOnClickListener(onClickListener("03 PM", "04 PM"));
        binding.fourpm.setOnClickListener(onClickListener("04 PM", "05 PM"));
        binding.fivepm.setOnClickListener(onClickListener("05 PM", "06 PM"));
        binding.sixpm.setOnClickListener(onClickListener("06 PM", "07 PM"));
        binding.sevenpm.setOnClickListener(onClickListener("07 PM", "08 PM"));
        binding.eightpm.setOnClickListener(onClickListener("08 PM", "09 PM"));
        binding.ninepm.setOnClickListener(onClickListener("09 PM", "10 PM"));
        binding.tenpm.setOnClickListener(onClickListener("10 PM", "11 PM"));
        binding.elevenpm.setOnClickListener(onClickListener("11 PM", "12 AM"));

        viewModel.getPlanner().observe(this, new Observer<List<Planner>>() {
            @Override
            public void onChanged(List<Planner> planners) {
                List<Planner> currentPlanners = new ArrayList<>();
                for (int i = 0; i < planners.size(); i++) {
                    if (planners.get(i).date.equals(currentlySelectedDate)) {
                        currentPlanners.add(planners.get(i));
                    }
                }

                updatePlanner(currentPlanners);
            }
        });
    }

    private void updatePlanner(List<Planner> currentPlanners) {
        for (int i = 0; i < currentPlanners.size(); i++) {
            Planner currentPlanner = currentPlanners.get(i);
            switch (currentPlanner.from) {
                case "12 AM": binding.twelveamSubject.setText(currentPlanner.subject);
            }
        }
    }

    private View.OnClickListener onClickListener(String startTime, String endTime) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show Dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(PlanActivity.this);
                builder.setMessage("From " + startTime + " to " + endTime);
                // Get the layout inflater
                LayoutInflater inflater = getLayoutInflater();
                // Inflate and set the layout for the dialog
                // Pass null as the parent view because its going in the dialog layout
                View view = inflater.inflate(R.layout.dialog, null);
                EditText subject = view.findViewById(R.id.editTextTime);

                NumberPicker picker = view.findViewById(R.id.numberpicker_main_picker);
                picker.setMaxValue(3);
                picker.setMinValue(0);
                String[] pickerVals  = new String[] {"Study", "Exercise", "Free Time", "Sleep"};
                picker.setDisplayedValues(pickerVals);

                builder.setView(view)
                        // Add action buttons
                        .setPositiveButton("Update", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                String subjectText = subject.getText().toString();
                                String category = pickerVals[picker.getValue()];

                                if (subjectText.equals("")) {
                                    Context context = getApplicationContext();
                                    CharSequence text = "fill out subject ";
                                    int duration = Toast.LENGTH_SHORT;

                                    Toast toast = Toast.makeText(context, text, duration);
                                    toast.show();
                                } else {
                                    viewModel.deleteItem(startTime, endTime, currentlySelectedDate);
                                    viewModel.insert(new Planner(subjectText, category, startTime, endTime, currentlySelectedDate));
                                }
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                            }
                        });
                builder.create().show();
            }
        };
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
        return new SimpleDateFormat("EEE", Locale.ENGLISH).format(calendar.getTime());
    }
}