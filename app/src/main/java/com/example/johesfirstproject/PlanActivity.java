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


        // initialisation with id's
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL));

        ArrayList<String> source = AddItemsToRecyclerViewArrayList();

        Adapter adapter = new Adapter(source);
        // Set Horizontal Layout Manager
        // for Recycler view

        // Set adapter on recycler view
        recyclerView.setAdapter(adapter);
    }

    // Function to add items in RecyclerView.
    public ArrayList<String> AddItemsToRecyclerViewArrayList()
    {
        Date currentTime = Calendar.getInstance().getTime();

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
        return new SimpleDateFormat("MM-dd-yyyy", Locale.getDefault()).format(calendar.getTime());
    }

    public class Adapter extends RecyclerView.Adapter<Adapter.MyView> {

        // List with String type
        private List<String> list;

        // View Holder class which
        // extends RecyclerView.ViewHolder
        public class MyView extends RecyclerView.ViewHolder {

            // Text View
            TextView textView;

            // parameterised constructor for View Holder class
            // which takes the view as a parameter
            public MyView(View view)
            {
                super(view);

                // initialise TextView with id
                textView = (TextView)view.findViewById(R.id.textview);
            }
        }

        // Constructor for adapter class
        // which takes a list of String type
        public Adapter(List<String> horizontalList)
        {
            this.list = horizontalList;
        }

        // Override onCreateViewHolder which deals
        // with the inflation of the card layout
        // as an item for the RecyclerView.
        @Override
        public MyView onCreateViewHolder(ViewGroup parent, int viewType)
        {
            // Inflate item.xml using LayoutInflator
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);

            // return itemView
            return new MyView(itemView);
        }

        // Override onBindViewHolder which deals
        // with the setting of different data
        // and methods related to clicks on
        // particular items of the RecyclerView.
        @Override
        public void onBindViewHolder(final MyView holder, final int position) {

            // Set the text of each item of
            // Recycler view with the list items
            holder.textView.setText(list.get(position));
        }

        // Override getItemCount which Returns
        // the length of the RecyclerView.
        @Override
        public int getItemCount() {
            return list.size();
        }
    }
}