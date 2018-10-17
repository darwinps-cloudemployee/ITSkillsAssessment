package com.assessment.it.itskillsassessment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class SelectActivity extends AppCompatActivity {

    int isadmin;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button takeExamButton = (Button) findViewById(R.id.button_take_exam);
        Button addQuestions = (Button) findViewById(R.id.button_add_questions);

        final Intent intent = getIntent();
        username = intent.getStringExtra("username");
        isadmin  = intent.getIntExtra("isadmin",0);

        if(isadmin == 0)
        {
            takeExamButton.setText("TAKE EXAM");

            if(takeExamButton.getText() == "TAKE EXAM")
            {
                takeExamButton.setOnClickListener(new Button.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(SelectActivity.this, DifficultySelectActivity.class);
                        intent.putExtra("username",username);
                        startActivity(intent);
                    }
                });
            }

            addQuestions.setVisibility(View.INVISIBLE);
        }

        if(isadmin > 0)
        {
            takeExamButton.setText("CHECK RESULTS");
                takeExamButton.setOnClickListener(new Button.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(SelectActivity.this, AllResultsActivity.class);
                        intent.putExtra("username",username);
                        startActivity(intent);
                    }
                });

            //takeExamButton.setVisibility(View.INVISIBLE);
        }




        addQuestions.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }

}
