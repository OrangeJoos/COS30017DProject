package com.example.babynamegenerator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

        readToList();

        printData();

        Button shareNames = findViewById(R.id.shareNamesButton);
        shareNames.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ListsToString = "Generated Baby Names:\n";
                for(int i = 0;i<babyNames.size();i++){
                    ListsToString += babyNames.get(i) + "\t";
                    Log.i("COUNT", String.valueOf(babyGenders.get(i).length()));
                    if(babyGenders.get(i).length() == 4){
                        ListsToString += "Boy" + "\n";
                    }
                    else{
                        ListsToString += "Girl" + "\n";
                    }
                }


                Intent shareSheet = new Intent(Intent.ACTION_SEND);
                shareSheet.putExtra(Intent.EXTRA_TEXT, ListsToString);
                shareSheet.setType("text/plain");
                startActivity(Intent.createChooser(shareSheet,"Share to..."));
            }
        });

    }

    private void readToList(){
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
    }

    private void printData(){
        for(int i=0;i<babyNames.size();i++){
            printName += babyNames.get(i) + "\n";
            int genderLength = babyGenders.get(i).length();
            if(genderLength == 4){ //Check if "MALE" or "FEMALE"
                printGender += "Boy" + "\n";
            }
            else{
                printGender += "Girl" + "\n";
            }

        }

        TextView nameText = findViewById(R.id.names);
        nameText.setText(printName);
        TextView genderText = findViewById(R.id.genders);
        genderText.setText(printGender);
    }
}
