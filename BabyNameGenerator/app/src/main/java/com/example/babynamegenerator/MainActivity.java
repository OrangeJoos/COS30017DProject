package com.example.babynamegenerator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button gennamebutton = findViewById(R.id.genButton);

        gennamebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent genNameIntent = new Intent(MainActivity.this, gender.class);
                startActivity(genNameIntent);
            }
        });

        Button showprevnamesButton = findViewById(R.id.showprevnamesButton);
        showprevnamesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent showNameIntent = new Intent(MainActivity.this, ShowPreviousNames.class);
                startActivity(showNameIntent);
            }
        });



    }
}
