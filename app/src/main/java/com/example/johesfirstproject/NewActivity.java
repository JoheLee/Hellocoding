package com.example.johesfirstproject;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.johesfirstproject.databinding.ActivityMainBinding;
import com.example.johesfirstproject.databinding.ActivityNewBinding;

public class NewActivity extends AppCompatActivity {

    ActivityNewBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityNewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.clickbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (binding.checkBox.isChecked()) {
                if (binding.checkBox.isChecked()) {
                    Context context = getApplicationContext();
                    CharSequence text = "on Toast!!!! " ;
                    int duration = Toast.LENGTH_SHORT ;

                    Toast toast = Toast. makeText(context, text, duration) ;
                    toast.show();

                    binding.checkBox.setChecked(false);
                } else if (binding.checkBox.isChecked() == false) {
                    Context context = getApplicationContext();
                    CharSequence text = "off Toast!!!! " ;
                    int duration = Toast.LENGTH_SHORT ;

                    Toast toast = Toast. makeText(context, text, duration) ;
                    toast.show();

                    binding.checkBox.setChecked(true);
                }
            }
        });


        String currentDate = "07/01/2021";
        binding.hello.setText("계획표 " + currentDate);
    }
}



