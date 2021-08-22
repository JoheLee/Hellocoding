package com.hellocodinng.johesfirstproject;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.hellocodinng.johesfirstproject.databinding.PlanactivityBinding;

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

        viewModel.getPlanner().observe(this, observer);

        binding.date1.setOnClickListener(updateSelectedDay(0, source, binding.date1));
        binding.date2.setOnClickListener(updateSelectedDay(1, source, binding.date2));
        binding.date3.setOnClickListener(updateSelectedDay(2, source, binding.date3));
        binding.date4.setOnClickListener(updateSelectedDay(3, source, binding.date4));
        binding.date5.setOnClickListener(updateSelectedDay(4, source, binding.date5));
        binding.date6.setOnClickListener(updateSelectedDay(5, source, binding.date6));
        binding.date7.setOnClickListener(updateSelectedDay(6, source, binding.date7));
    }

    private View.OnClickListener updateSelectedDay(int day, ArrayList<String> source, TextView textView) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.date1.setTextColor(Color.parseColor("#a8a5a5"));
                binding.date2.setTextColor(Color.parseColor("#a8a5a5"));
                binding.date3.setTextColor(Color.parseColor("#a8a5a5"));
                binding.date4.setTextColor(Color.parseColor("#a8a5a5"));
                binding.date5.setTextColor(Color.parseColor("#a8a5a5"));
                binding.date6.setTextColor(Color.parseColor("#a8a5a5"));
                binding.date7.setTextColor(Color.parseColor("#a8a5a5"));

                textView.setTextColor(Color.parseColor("#000000"));
                currentlySelectedDate = source.get(day);
                viewModel.getPlanner().removeObserver(observer);
                viewModel.getPlanner().observe(PlanActivity.this, observer);
            }
        };
    }

    private Observer<List<Planner>> observer = new Observer<List<Planner>>() {
        @Override
        public void onChanged(List<Planner> planners) {
            clear();
            List<Planner> currentPlanners = new ArrayList<>();
            for (int i = 0; i < planners.size(); i++) {
                if (planners.get(i).date.equals(currentlySelectedDate)) {
                    currentPlanners.add(planners.get(i));
                }
            }

            updatePlanner(currentPlanners);
        }
    };

    private void clear() {
        binding.twelveamSubject.setText("");
        binding.twelveamSubject.setBackgroundColor(Color.parseColor("#ffffff"));
        binding.oneamTimeSubject.setText("");
        binding.oneamTimeSubject.setBackgroundColor(Color.parseColor("#ffffff"));
        binding.twoamTimeSubject.setText("");
        binding.twoamTimeSubject.setBackgroundColor(Color.parseColor("#ffffff"));
        binding.threeamTimeSubject.setText("");
        binding.threeamTimeSubject.setBackgroundColor(Color.parseColor("#ffffff"));
        binding.fouramTimeSubject.setText("");
        binding.fouramTimeSubject.setBackgroundColor(Color.parseColor("#ffffff"));
        binding.fiveamTimeSubject.setText("");
        binding.fiveamTimeSubject.setBackgroundColor(Color.parseColor("#ffffff"));
        binding.sixamTimeSubject.setText("");
        binding.sixamTimeSubject.setBackgroundColor(Color.parseColor("#ffffff"));
        binding.sevenamTimeSubject.setText("");
        binding.sevenamTimeSubject.setBackgroundColor(Color.parseColor("#ffffff"));
        binding.eightamTimeSubject.setText("");
        binding.eightamTimeSubject.setBackgroundColor(Color.parseColor("#ffffff"));
        binding.nineamTimeSubject.setText("");
        binding.nineamTimeSubject.setBackgroundColor(Color.parseColor("#ffffff"));
        binding.tenamTimeSubject.setText("");
        binding.tenamTimeSubject.setBackgroundColor(Color.parseColor("#ffffff"));
        binding.elevenamTimeSubject.setText("");
        binding.elevenamTimeSubject.setBackgroundColor(Color.parseColor("#ffffff"));
        binding.twelvepmTimeSubject.setText("");
        binding.twelvepmTimeSubject.setBackgroundColor(Color.parseColor("#ffffff"));
        binding.onepmTimeSubject.setText("");
        binding.onepmTimeSubject.setBackgroundColor(Color.parseColor("#ffffff"));
        binding.twopmTimeSubject.setText("");
        binding.twopmTimeSubject.setBackgroundColor(Color.parseColor("#ffffff"));
        binding.threepmTimeSubject.setText("");
        binding.threepmTimeSubject.setBackgroundColor(Color.parseColor("#ffffff"));
        binding.fourpmTimeSubject.setText("");
        binding.fourpmTimeSubject.setBackgroundColor(Color.parseColor("#ffffff"));
        binding.fivepmTimeSubject.setText("");
        binding.fivepmTimeSubject.setBackgroundColor(Color.parseColor("#ffffff"));
        binding.sixpmTimeSubject.setText("");
        binding.sixpmTimeSubject.setBackgroundColor(Color.parseColor("#ffffff"));
        binding.sevenpmTimeSubject.setText("");
        binding.sevenpmTimeSubject.setBackgroundColor(Color.parseColor("#ffffff"));
        binding.eightpmTimeSubject.setText("");
        binding.eightpmTimeSubject.setBackgroundColor(Color.parseColor("#ffffff"));
        binding.ninepmTimeSubject.setText("");
        binding.ninepmTimeSubject.setBackgroundColor(Color.parseColor("#ffffff"));
        binding.tenpmTimeSubject.setText("");
        binding.tenpmTimeSubject.setBackgroundColor(Color.parseColor("#ffffff"));
        binding.elevenpmTimeSubject.setText("");
        binding.elevenpmTimeSubject.setBackgroundColor(Color.parseColor("#ffffff"));
    }

    private void updatePlanner(List<Planner> currentPlanners) {
        for (int i = 0; i < currentPlanners.size(); i++) {
            Planner currentPlanner = currentPlanners.get(i);
            TextView textView = null;
            switch (currentPlanner.from) {
                case "12 AM": textView = binding.twelveamSubject;break;
                case "01 AM": textView = binding.oneamTimeSubject;break;
                case "02 AM": textView = binding.twoamTimeSubject;break;
                case "03 AM": textView = binding.threeamTimeSubject;break;
                case "04 AM": textView = binding.fouramTimeSubject;break;
                case "05 AM": textView = binding.fiveamTimeSubject;break;
                case "06 AM": textView = binding.sixamTimeSubject;break;
                case "07 AM": textView = binding.sevenamTimeSubject;break;
                case "08 AM": textView = binding.eightamTimeSubject;break;
                case "09 AM": textView = binding.nineamTimeSubject;break;
                case "10 AM": textView = binding.tenamTimeSubject;break;
                case "11 AM": textView = binding.elevenamTimeSubject;break;
                case "12 PM": textView = binding.twelvepmTimeSubject;break;
                case "01 PM": textView = binding.onepmTimeSubject;break;
                case "02 PM": textView = binding.twopmTimeSubject;break;
                case "03 PM": textView = binding.threepmTimeSubject;break;
                case "04 PM": textView = binding.fourpmTimeSubject;break;
                case "05 PM": textView = binding.fivepmTimeSubject;break;
                case "06 PM": textView = binding.sixpmTimeSubject;break;
                case "07 PM": textView = binding.sevenpmTimeSubject;break;
                case "08 PM": textView = binding.eightpmTimeSubject;break;
                case "09 PM": textView = binding.ninepmTimeSubject;break;
                case "10 PM": textView = binding.tenpmTimeSubject;break;
                case "11 PM": textView = binding.elevenpmTimeSubject;break;
            }
            updatePlannerContent(textView, currentPlanner);
        }
    }

    private void updatePlannerContent(TextView textView, Planner currentPlanner) {
        textView.setText(currentPlanner.subject);
        textView.setTextColor(Color.parseColor("#ffffff"));
        if (currentPlanner.category.equals("Study")) {
            textView.setBackgroundColor(Color.parseColor("#c9b2d9"));
        } else if (currentPlanner.category.equals("Exercise")) {
            textView.setBackgroundColor(Color.parseColor("#7fbfbf"));
        } else if (currentPlanner.category.equals("Free Time")) {
            textView.setBackgroundColor(Color.parseColor("#b3f3f2"));
        } else if (currentPlanner.category.equals("Sleep")) {
            textView.setBackgroundColor(Color.parseColor("#f2d577"));
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
                                    viewModel.insert(new Planner(subjectText, category, startTime, endTime, currentlySelectedDate));
                                }
                            }
                        })
                        .setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                viewModel.deleteItem(startTime, endTime, currentlySelectedDate);
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