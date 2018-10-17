package com.assessment.it.itskillsassessment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class DifficultySelectActivity extends AppCompatActivity {

    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_difficulty_select);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Button easyButton = (Button) findViewById(R.id.button_easy);
        Button intermediateButton = (Button) findViewById(R.id.button_intermediate);
        Button advancedButton = (Button) findViewById(R.id.button_advanced);

        Intent intent = getIntent();
        username = intent.getStringExtra("username");

        easyButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {


                    String option = "Easy";
                    Intent intent = new Intent(DifficultySelectActivity.this,ExamActivity.class);
                    intent.putExtra("Option",option);
                    intent.putExtra("username", username);
                    startActivity(intent);


            }
        });
        intermediateButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {


                String option = "Intermediate";
                Intent intent = new Intent(DifficultySelectActivity.this,ExamActivity.class);
                intent.putExtra("Option",option);
                intent.putExtra("username", username);
                startActivity(intent);


            }
        });
        advancedButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {


                String option = "Advanced";
                Intent intent = new Intent(DifficultySelectActivity.this,ExamActivity.class);
                intent.putExtra("Option",option);
                intent.putExtra("username", username);
                startActivity(intent);


            }
        });

    }

}
