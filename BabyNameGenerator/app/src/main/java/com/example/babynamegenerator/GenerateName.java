package com.example.babynamegenerator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class GenerateName extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_name);

        Intent getIntent = getIntent();
        int startYear = getIntent.getIntExtra("startYear", 2008);
        int endYear = getIntent.getIntExtra("endYear", 2018);
        int genderint = getIntent.getIntExtra("gender", 0);
        TextView genderText = findViewById(R.id.testGender);
        switch(genderint){
            case 0:
                genderText.setText("Boy");
                break;
            case 1:
                genderText.setText("Girl");
                break;
        }
        TextView startYearText = findViewById(R.id.testStartYear);
        startYearText.setText(String.valueOf(startYear));
        TextView endYearText = findViewById(R.id.testEndYear);
        endYearText.setText(String.valueOf(endYear));
    }
}
