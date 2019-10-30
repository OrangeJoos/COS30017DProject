package com.example.babynamegenerator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.Toast;

public class YearRange extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_year_range);

        Button confirm = findViewById(R.id.confirmYears);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckBox ignoreYears = findViewById(R.id.ignoreDate);
                if(ignoreYears.isSelected()){
                    passData(2008,2018);
                }


                Spinner startYearSpinner = findViewById(R.id.startYear);
                int startYear = Integer.parseInt(startYearSpinner.getSelectedItem().toString());
                Spinner endYearSpinner = findViewById(R.id.testEndYear);
                int endYear = Integer.parseInt(endYearSpinner.getSelectedItem().toString());

                if(startYear > endYear){
                    Toast error = Toast.makeText(getApplicationContext(), "That year range is unavailable, please try again", Toast.LENGTH_LONG);
                    error.show();
                }
                else{
                    Intent getGender = getIntent();
                    int gender = getGender.getIntExtra("gender", 0);
                    Intent generateName = new Intent(YearRange.this, GenerateName.class);
                    generateName.putExtra("gender", gender);
                    generateName.putExtra("startYear", startYear);
                    generateName.putExtra("endYear", endYear);
                    startActivity(generateName);
                }

            }
        });
    }

    void passData(int startYear, int endYear){

    }
}
