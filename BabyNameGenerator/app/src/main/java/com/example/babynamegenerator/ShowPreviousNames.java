package com.example.babynamegenerator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ShowPreviousNames extends AppCompatActivity {
    private List<String> babyNames;
    private List<String> babyGenders;
    private String printName = "";
    private String printGender = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_previous_names);
        babyNames = new ArrayList<>();
        babyGenders = new ArrayList<>();


        FileInputStream file = null;
        try {
            file = openFileInput("baby_names.txt");
            InputStreamReader inputstream = new InputStreamReader(file);
            BufferedReader reader = new BufferedReader(inputstream);
            String data;

            while((data = reader.readLine()) != null){
                String[] splits = data.split(",");

                babyNames.add(splits[0]);
                babyGenders.add(splits[1]);
            }
            file.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        for(int i=0;i<babyNames.size();i++){
            printName += babyNames.get(i) + "\n";
            printGender += babyGenders.get(i) + "\n";
        }

        Log.i("PRINT NAME", printName);
        Log.i("PRINT GENDER", printGender);

        TextView nameText = findViewById(R.id.names);
        nameText.setText(printName);

        TextView genderText = findViewById(R.id.genders);
        genderText.setText(printGender);
    }
}
