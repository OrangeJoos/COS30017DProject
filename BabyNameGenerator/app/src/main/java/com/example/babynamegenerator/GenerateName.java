package com.example.babynamegenerator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Random;

public class GenerateName extends AppCompatActivity {
    private TextView name;
    private String babyName;
    private String babyGender;
    Random rand = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_name);


        Intent getIntent = getIntent();
        int startYear = getIntent.getIntExtra("startYear", 2008);
        int endYear = getIntent.getIntExtra("endYear", 2018);
        int genderint = getIntent.getIntExtra("gender", 0);
        TextView genderText = findViewById(R.id.genderText);
        String gender = "";
        switch(genderint){
            case 0:
                gender = "MALE";
                genderText.setText("Boy's");
                break;
            case 1:
                gender = "FEMALE";
                genderText.setText("Girl's");
                break;
        }

        int usedYear = rand.nextInt((endYear-startYear) + 1) + startYear;
        int position = rand.nextInt(100) + 1; //Position
        Log.i("GENDER", gender);
        Log.i("USED YEAR", String.valueOf(usedYear));
        Log.i("POSITION", String.valueOf(position));
        name = findViewById(R.id.nameText);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.vic.gov.au:443/bdm/names/v1.0/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PopularBabyNamesAPI popularBabyNamesAPI = retrofit.create(PopularBabyNamesAPI.class);

        Call<BabyList> call = popularBabyNamesAPI.getPosts(usedYear,gender,position);
        call.enqueue(new Callback<BabyList>() {
            @Override
            public void onResponse(Call<BabyList> call, Response<BabyList> response) {
                if(!response.isSuccessful()){
                    name.setText("Code:" + response.code());
                    return;
                }
                BabyList babies = response.body();
                Baby baby = babies.getBabies().get(0);
                babyName = baby.getName();
                babyGender = baby.getSex();
                Log.i("POST", baby.getName());
                name.setText(babyName);
               }


            @Override
            public void onFailure(Call<BabyList> call, Throwable t) {
                name.setText("FAILURE" + t.getMessage());
            }
        });


        Button saveName = findViewById(R.id.saveName);
        saveName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FileOutputStream file = null;
                try {
                    file = openFileOutput("baby_names.txt", MODE_APPEND);
                    String vals = babyName + "," + babyGender;
                    file.write(vals.getBytes());
                    file.write(("\n").getBytes());
                    finish();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    if(file != null){
                        try {
                            file.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }


                Toast save = Toast.makeText(getApplicationContext(), "Your name has been saved!", Toast.LENGTH_LONG);
                save.show();
            }
        });

        Button shareName = findViewById(R.id.shareButton);
        shareName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent shareSheet = new Intent(Intent.ACTION_SEND);
                shareSheet.putExtra(Intent.EXTRA_TEXT, "Your new baby's name is NAME congratulations!");
                shareSheet.setType("text/plain");
                startActivity(Intent.createChooser(shareSheet,"Share Name to..."));
            }
        });

        Button restartNameGen = findViewById(R.id.repeatButton);
        restartNameGen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent genName = new Intent(GenerateName.this, gender.class);
                startActivity(genName);
                finish();
            }
        });
    }
}
