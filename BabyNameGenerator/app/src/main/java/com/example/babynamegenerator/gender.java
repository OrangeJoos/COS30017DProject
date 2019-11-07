package com.example.babynamegenerator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import java.util.Random;
import java.time.Year;

public class gender extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gender);
        final Random random = new Random();


        Button boyButton = findViewById(R.id.boyButton);
        boyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextActivity(0);
            }
        });

        Button girlButton = findViewById(R.id.girlButton);
        girlButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextActivity(1);
            }
        });

        Button eitherButton = findViewById(R.id.eitherButton);
        eitherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextActivity(random.nextInt(2));
            }
        });
    }


    void nextActivity(int Gender){
        Intent genderactivity = new Intent(gender.this, YearRange.class);
        genderactivity.putExtra("gender", Gender);
        startActivity(genderactivity);
        finish();
    }
}
